package com.unibuc.forumApi.repository;

import com.unibuc.forumApi.model.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CommentRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Comment> getComment(int id) {
        String sql = "select * from comment where id = " + id;
        RowMapper<Comment> mapper = getCommentRowMapper();

        return getCommentFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<Comment>> getComments() {
        String sql = "select * from comment";
        RowMapper<Comment> mapper = getCommentRowMapper();

        return getCommentsFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<Comment>> getUserComments(int userId) {
        String sql = "select * from Comment where user_id = " + userId;
        RowMapper<Comment> mapper = getCommentRowMapper();

        return getCommentsFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<Comment>> getTopicComments(int topicId) {
        String sql = "select * from Comment where topic_id = " + topicId;
        RowMapper<Comment> mapper = getCommentRowMapper();

        return getCommentsFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    private RowMapper<Comment> getCommentRowMapper() {
        return (resultSet, rowNum) -> new Comment(
                resultSet.getInt("id"),
                resultSet.getString("description"),
                resultSet.getInt("topic_id"),
                resultSet.getInt("user_id")
        );
    }

    private Optional<List<Comment>> getCommentsFromResultSet(List<Comment> comments) {
        if (comments != null && !comments.isEmpty()) {
            return Optional.of(comments);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Comment> getCommentFromResultSet(List<Comment> comments) {
        if (comments != null && !comments.isEmpty()) {
            return Optional.of(comments.get(0));
        } else {
            return Optional.empty();
        }
    }

    public Comment update(Comment comment) {
        String updateSql = "update comment set " +
                "description = :description," +
                "topic_id = :topic_id," +
                "user_id = :user_id " +
                "where id = :id";

        String insertSql = "insert into comment (description, topic_id, user_id) "
                + "values (:description, :topic_id, :user_id)";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", comment.getId())
                .addValue("description", comment.getDescription())
                .addValue("topic_id", comment.getTopicId())
                .addValue("user_id", comment.getUserId());
        if(jdbcTemplate.update(updateSql, parameters) != 1) {
            jdbcTemplate.update(insertSql, parameters);
        }
        return comment;
    }

    public void delete(int id) {
        String sql = "delete from comment where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbcTemplate.update(sql, parameters);
    }
}

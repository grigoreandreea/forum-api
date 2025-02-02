package com.unibuc.forumApi.repository;

import com.unibuc.forumApi.model.Topic;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TopicRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TopicRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Topic> getTopic(int id) {
        String sql = "select * from topic where id = " + id;
        RowMapper<Topic> mapper = getTopicRowMapper();

        return getTopicFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<Topic>> getTopics() {
        String sql = "select * from topic";
        RowMapper<Topic> mapper = getTopicRowMapper();

        return getTopicsFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<Topic>> getUserTopics(int userId) {
        String sql = "select * from topic where user_id =" + userId;
        RowMapper<Topic> mapper = getTopicRowMapper();

        return getTopicsFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<Topic>> getCategoryTopics(int categoryId) {
        String sql = "select * from topic where category_id =" + categoryId;
        RowMapper<Topic> mapper = getTopicRowMapper();

        return getTopicsFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    private RowMapper<Topic> getTopicRowMapper() {
        return (resultSet, rowNum) -> new Topic(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getInt("user_id"),
                resultSet.getInt("category_id")
        );
    }

    private Optional<List<Topic>> getTopicsFromResultSet(List<Topic> topics) {
        if (topics != null && !topics.isEmpty()) {
            return Optional.of(topics);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Topic> getTopicFromResultSet(List<Topic> topics) {
        if (topics != null && !topics.isEmpty()) {
            return Optional.of(topics.get(0));
        } else {
            return Optional.empty();
        }
    }

    public Topic update(Topic topic) {
        String updateSql = "update topic set " +
                "name = :name," +
                "description = :description," +
                "user_id = :user_id," +
                "category_id = :category_id " +
                "where id = :id";

        String insertSql = "insert into topic (name, description, user_id, category_id) "
                + "values (:name, :description, :user_id, :category_id)";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", topic.getId())
                .addValue("name", topic.getName())
                .addValue("description", topic.getDescription())
                .addValue("user_id", topic.getUserId())
                .addValue("category_id", topic.getCategoryId());

        if(jdbcTemplate.update(updateSql, parameters) != 1) {
            jdbcTemplate.update(insertSql, parameters);
        }
        return topic;
    }

    public void delete(int id) {
        String sql = "delete from topic where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbcTemplate.update(sql, parameters);
    }
}

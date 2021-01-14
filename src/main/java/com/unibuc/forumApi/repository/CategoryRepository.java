package com.unibuc.forumApi.repository;

import com.unibuc.forumApi.model.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CategoryRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<Category>> getCategories() {
        String sql = "select * from category";
        RowMapper<Category> mapper = getCategoryRowMapper();

        return getCategoriesFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<Category> getCategory(int id) {
        String sql = "select * from category where id=" + id;
        RowMapper<Category> mapper = getCategoryRowMapper();

        return getCategoryFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Category update(Category category) {
        String updateSql = "update category set "
                + "name = :name "
                + "where id = :id";

        String insertSql = "insert into category (name) "
                + "values (:name) ";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", category.getId())
                .addValue("name", category.getName());

        if(jdbcTemplate.update(updateSql, parameters) != 1) {
            jdbcTemplate.update(insertSql, parameters);
        }
        return category;
    }

    public void delete(int id) {
        String sql = "delete from category where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbcTemplate.update(sql, parameters);
    }

    private Optional<List<Category>> getCategoriesFromResultSet(List<Category> categories) {
        if (categories != null && !categories.isEmpty()) {
            return Optional.of(categories);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Category> getCategoryFromResultSet(List<Category> category) {
        if (category != null && !category.isEmpty()) {
            return Optional.of(category.get(0));
        } else {
            return Optional.empty();
        }
    }

    private RowMapper<Category> getCategoryRowMapper() {
        return (resultSet, rowNum) -> new Category(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }

}

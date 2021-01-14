package com.unibuc.forumApi.repository;

import com.unibuc.forumApi.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<User>> getUsers() {
        String sql = "select * from users";
        RowMapper<User> mapper = getUserRowMapper();

        return getUsersFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<User> getUser(int id) {
        String sql = "select * from users where id=" + id;
        RowMapper<User> mapper = getUserRowMapper();

        return getUserFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public User update(User user) {
        String updateSql = "update users set "
                + "username = :username, "
                + "date = :date, "
                + "gender = :gender, "
                + "country_id = :country_id,"
                + "city_id = :city_id "
                + "where id = :id";

        String insertSql = "insert into users (username, date, gender, country_id, city_id) "
                + "values (:username, :date, :gender, :country_id, :city_id) ";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("username", user.getUsername())
                .addValue("date", user.getDate())
                .addValue("gender", user.isGender())
                .addValue("country_id", user.getCountryId())
                .addValue("city_id", user.getCityId());

        if(jdbcTemplate.update(updateSql, parameters) != 1) {
            jdbcTemplate.update(insertSql, parameters);
        }
        return user;
    }

    public void delete(int id) {
        String sql = "delete from users where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbcTemplate.update(sql, parameters);
    }

    private Optional<List<User>> getUsersFromResultSet(List<User> users) {
        if (users != null && !users.isEmpty()) {
            return Optional.of(users);
        } else {
            return Optional.empty();
        }
    }

    private Optional<User> getUserFromResultSet(List<User> users) {
        if (users != null && !users.isEmpty()) {
            return Optional.of(users.get(0));
        } else {
            return Optional.empty();
        }
    }

    private RowMapper<User> getUserRowMapper() {
        return (resultSet, rowNum) -> {
/*            System.out.println("HERE:");
            System.out.println(resultSet.getMetaData().getColumnCount());
            int columnCount = resultSet.getMetaData().getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.println("column name: " + resultSet.getMetaData().getColumnName(i));
            }*/
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getDate("date"),
                    resultSet.getBoolean("gender"),
                    resultSet.getInt("country_id"),
                    resultSet.getInt("city_id")
            );
        };
    }


}

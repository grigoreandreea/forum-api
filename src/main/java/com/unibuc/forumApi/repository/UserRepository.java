package com.unibuc.forumApi.repository;

import com.unibuc.forumApi.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.*;

import java.util.ArrayList;
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

    public Optional<User> findUserByName(String username) {
        String sql = "select * from users where username='" + username + "';";
        RowMapper<User> mapper = getUserRowMapper();

        return getUserFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<User>> getUsersByCompany(int companyId) {
        String sql = "select u.id, u.username, u.date, u.gender, u.country_id, u.city_id " +
                "from users u join user_works_on_company uwoc on u.id = uwoc.user_id " +
                "where uwoc.company_id = " + companyId;
        RowMapper<User> mapper = getUserRowMapper();
        return getUsersFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public User update(User user) {
        String updateSql = "update users set "
                + "username = :username, "
                + "password = :password, "
                + "date = :date, "
                + "gender = :gender, "
                + "country_id = :country_id,"
                + "city_id = :city_id "
                + "where id = :id";

        String insertSql = "insert into users (username, password, date, gender, country_id, city_id) "
                + "values (:username, :password, :date, :gender, :country_id, :city_id) ";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
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
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getDate("date"),
                    resultSet.getBoolean("gender"),
                    resultSet.getInt("country_id"),
                    resultSet.getInt("city_id")
            );
        };
    }

    public void deleteExistingUserEmployers(int id) {
        String sql = "delete from user_works_on_company where user_id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbcTemplate.update(sql, parameters);
    }

    public void saveUserEmployers(int userId, List<Integer> companies) {
        String values = "";
        for(int i = 0; i < companies.size(); i++) {
            values += "( " + userId + ", " + companies.get(i) + " )";
            if (i != companies.size() - 1) {
                values += ",";
            }
        }

        String insertSql = "insert into user_works_on_company (user_id, company_id) "
                + "values " + values;

        jdbcTemplate.update(insertSql, new MapSqlParameterSource());
    }
}

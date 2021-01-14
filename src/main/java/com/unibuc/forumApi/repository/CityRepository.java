package com.unibuc.forumApi.repository;

import com.unibuc.forumApi.model.City;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CityRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CityRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<City> getCity(int id) {
        String sql = "select * from city where id = " + id;
        RowMapper<City> mapper = getCityRowMapper();

        return getCityFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<City>> getCities() {
        String sql = "select * from city";
        RowMapper<City> mapper = getCityRowMapper();

        return getCitiesFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<City>> getCountryCities(int countryId) {
        String sql = "select * from city where country_id =" + countryId;
        RowMapper<City> mapper = getCityRowMapper();

        return getCitiesFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    private Optional<City> getCityFromResultSet(List<City> cities) {
        if (cities != null && !cities.isEmpty()) {
            return Optional.of(cities.get(0));
        } else {
            return Optional.empty();
        }
    }

    private Optional<List<City>> getCitiesFromResultSet(List<City> cities) {
        if (cities != null && !cities.isEmpty()) {
            return Optional.of(cities);
        } else {
            return Optional.empty();
        }
    }

    private RowMapper<City> getCityRowMapper() {
        return (resultSet, rowNum) -> new City(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("country_id")
        );
    }

    public City update(City city) {
        String updateSql = "update city set " +
                "name = :name," +
                "country_id = :country_id " +
                "where id = :id";

        String insertSql = "insert into city (name, country_id) "
                + "values (:name, :country_id)";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", city.getId())
                .addValue("name", city.getName())
                .addValue("country_id", city.getCountryId());

        if(jdbcTemplate.update(updateSql, parameters) != 1) {
            jdbcTemplate.update(insertSql, parameters);
        }
        return city;
    }

    public void delete(int id) {
        String sql = "delete from city where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbcTemplate.update(sql, parameters);
    }
}

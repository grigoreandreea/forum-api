package com.unibuc.forumApi.repository;

import com.unibuc.forumApi.model.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CountryRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<Country>> getCountries() {
        String sql = "select * from country";
        RowMapper<Country> mapper = getCountryRowMapper();

        return getCountriesFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<Country> getCountry(int id) {
        String sql = "select * from country where id=" + id;
        RowMapper<Country> mapper = getCountryRowMapper();

        return getCountryFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Country update(Country country) {
        String updateSql = "update country set "
                + "name = :name "
                + "where id = :id";

        String insertSql = "insert into country (name) "
                + "values (:name) ";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", country.getId())
                .addValue("name", country.getName());

        if(jdbcTemplate.update(updateSql, parameters) != 1) {
            jdbcTemplate.update(insertSql, parameters);
        }
        return country;
    }

    public void delete(int id) {
        String sql = "delete from country where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbcTemplate.update(sql, parameters);
    }

    private Optional<List<Country>> getCountriesFromResultSet(List<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            return Optional.of(countries);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Country> getCountryFromResultSet(List<Country> country) {
        if (country != null && !country.isEmpty()) {
            return Optional.of(country.get(0));
        } else {
            return Optional.empty();
        }
    }

    private RowMapper<Country> getCountryRowMapper() {
        return (resultSet, rowNum) -> {
            return new Country(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        };
    }
}

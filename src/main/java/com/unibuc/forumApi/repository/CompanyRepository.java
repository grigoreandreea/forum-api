package com.unibuc.forumApi.repository;

import com.unibuc.forumApi.model.Company;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CompanyRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<Company>> getCompanies() {
        String sql = "select * from company";
        RowMapper<Company> mapper = getCompanyRowMapper();

        return getCompaniesFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<Company> getCompany(int id) {
        String sql = "select * from company where id=" + id;
        RowMapper<Company> mapper = getCompanyRowMapper();

        return getCompanyFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Optional<List<Company>> getCompaniesByUser(int userId) {
        String sql = "select c.id, c.name " +
                "from company c join user_works_on_company uwoc on c.id = uwoc.company_id " +
                "where uwoc.user_id = " + userId;
        RowMapper<Company> mapper = getCompanyRowMapper();

        return getCompaniesFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    public Company update(Company company) {
        String updateSql = "update company set "
                + "name = :name "
                + "where id = :id";

        String insertSql = "insert into company (name) "
                + "values (:name) ";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", company.getId())
                .addValue("name", company.getName());

        if(jdbcTemplate.update(updateSql, parameters) != 1) {
            jdbcTemplate.update(insertSql, parameters);
        }
        return company;
    }

    public void delete(int id) {
        String sql = "delete from company where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        jdbcTemplate.update(sql, parameters);
    }

    private Optional<List<Company>> getCompaniesFromResultSet(List<Company> companies) {
        if (companies != null && !companies.isEmpty()) {
            return Optional.of(companies);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Company> getCompanyFromResultSet(List<Company> companies) {
        if (companies != null && !companies.isEmpty()) {
            return Optional.of(companies.get(0));
        } else {
            return Optional.empty();
        }
    }

    private RowMapper<Company> getCompanyRowMapper() {
        return (resultSet, rowNum) -> {
            return new Company(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        };
    }
}

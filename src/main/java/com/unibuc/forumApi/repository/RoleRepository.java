package com.unibuc.forumApi.repository;

import com.unibuc.forumApi.model.ERole;
import com.unibuc.forumApi.model.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.*;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RoleRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Role> findByName(ERole role) {
        String sql = "select * from authorities where authority=" + role;
        RowMapper<Role> mapper = getRoleRowMapper();

        return getRoleFromResultSet(jdbcTemplate.query(sql, mapper));
    }

    private Optional<Role> getRoleFromResultSet(List<Role> roles) {
        if (roles != null && !roles.isEmpty()) {
            return Optional.of(roles.get(0));
        } else {
            return Optional.empty();
        }
    }

    private RowMapper<Role> getRoleRowMapper() {
        return (resultSet, rowNum) -> {
            return new Role(
                    ERole.ROLE_USER
            );
        };
    }

}

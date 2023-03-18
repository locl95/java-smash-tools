package org.kos.smashcharacters.drivenadapters.postgres;

import org.kos.util.either.Either;
import org.kos.util.either.Left;
import org.kos.util.either.Right;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class SpringJdbcHelper {
    JdbcTemplate jdbcTemplate;

    public SpringJdbcHelper(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public <T> Either<Exception, List<T>> query(String query, RowMapper<T> mapper) {
        try {
            List<T> result = jdbcTemplate.query(query, mapper);
            return new Right<>(result);
        } catch (DataAccessException e) {
            return new Left<>(e);
        }
    }

}

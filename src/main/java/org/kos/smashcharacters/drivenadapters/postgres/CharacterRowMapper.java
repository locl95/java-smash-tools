package org.kos.smashcharacters.drivenadapters.postgres;

import org.kos.smashcharacters.domain.Character;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterRowMapper implements RowMapper<Character> {
    @Override
    public Character mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Character(rs.getString("slug"), rs.getString("name"));
    }
}
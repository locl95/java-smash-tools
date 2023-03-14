package org.kos.smashcharacters.drivenadapters.postgres;

import org.kos.db.sql.Sql;
import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterRepository;
import org.kos.util.either.Either;
import org.kos.util.either.Left;
import org.kos.util.either.Right;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class CharacterPostgresRepository implements CharacterRepository {

    DataSource ds;
    Sql sql;

    public CharacterPostgresRepository(DataSource ds) {
        this.ds = ds;
        this.sql = new Sql();
    }

    @Override
    public Either<Exception, List<Character>> getCharacters() {
        String select = "select * from characters";
        try (Connection conn = ds.getConnection()) {
            Function<ResultSet, Either<Exception, Character>> reader = rs -> {
                try {
                    return new Right<>(new Character(rs.getString(1), rs.getString(2)));
                } catch (SQLException e) {
                    return new Left<>(e);
                }
            };
            return sql.select(conn, select, reader);
        } catch (SQLException e) {
            return new Left<>(e);
        }
    }
}

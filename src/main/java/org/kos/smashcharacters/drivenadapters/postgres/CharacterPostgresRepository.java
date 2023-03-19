package org.kos.smashcharacters.drivenadapters.postgres;

import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterRepository;
import org.kos.util.either.Either;

import javax.sql.DataSource;
import java.util.List;

public class CharacterPostgresRepository implements CharacterRepository {

    DataSource ds;
    SpringJdbcHelper springHelper;

    public CharacterPostgresRepository(DataSource ds) {
        this.ds = ds;
        this.springHelper = new SpringJdbcHelper(ds);
    }

    @Override
    public Either<Exception, List<Character>> getCharacters() {
        String select = "select * from characters";
        return springHelper.query(select, new CharacterRowMapper());
    }
}

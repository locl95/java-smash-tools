package org.kos.smashcharacters.drivenadapters.postgres;

import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterRepository;
import org.kos.smashcharacters.domain.errors.character.CharacterDuplicated;
import org.kos.smashcharacters.domain.errors.character.CharacterError;
import org.kos.smashcharacters.domain.errors.character.CharacterUnexpectedError;
import org.kos.util.either.Either;

import javax.sql.DataSource;
import java.util.List;
import java.util.function.Function;

//TODO: Insert characters batch mode instead of one by one

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

    @Override
    public Either<CharacterError, Integer> insertCharacter(Character character) {
        String insert = "insert into characters(slug, name) values(?,?)";
        Either<Exception, Integer> result = springHelper.update(insert, character.slug(), character.name());
        return result.mapLeft(error -> {
            if (error.getMessage().contains("already exists")) return new CharacterDuplicated(character.slug());
            else return new CharacterUnexpectedError(error.getMessage());
        });
    }

    @Override
    public void setState(List<Character> characters) {
        characters.forEach(this::insertCharacter);
    }

    @Override
    public List<Character> getState() {
        return getCharacters().right().get();
    }
}

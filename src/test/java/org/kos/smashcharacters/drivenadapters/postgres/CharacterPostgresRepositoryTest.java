package org.kos.smashcharacters.drivenadapters.postgres;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kos.db.FlywayMigrations;
import org.kos.db.hikari.HikariCP;
import org.kos.smashcharacters.domain.CharacterRepositoryTest;
import org.kos.smashcharacters.drivenadapters.memory.CharacterInMemoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class CharacterPostgresRepositoryTest implements CharacterRepositoryTest {

    HikariDataSource ds = new HikariCP().dsFrom("jdbc:postgresql://localhost/smashtools", "postgres", "postgres");
    Flyway flyway = new FlywayMigrations().flywayFrom(ds);

    @BeforeEach
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Override
    @Test
    public void GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters() {
        CharacterPostgresRepository repo = new CharacterPostgresRepository(ds);
        _GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(repo);
    }

    @Override
    @Test
    public void GivenACharacterRepositoryIShouldBeAbleToInsertCharacters() {
        CharacterPostgresRepository repo = new CharacterPostgresRepository(ds);
        _GivenACharacterRepositoryIShouldBeAbleToInsertCharacters(repo);
    }
}

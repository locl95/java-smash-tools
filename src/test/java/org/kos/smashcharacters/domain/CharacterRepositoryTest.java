package org.kos.smashcharacters.domain;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.kos.db.FlywayMigrations;
import org.kos.db.hikari.HikariCP;
import org.kos.smashcharacters.drivenadapters.memory.CharacterInMemoryRepository;
import org.kos.smashcharacters.drivenadapters.postgres.CharacterPostgresRepository;
import org.kos.smashcharacters.drivenadapters.scrapping.CharacterScrappingRepository;
import org.kos.smashcharacters.helpers.TestValues;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;


public class CharacterRepositoryTest {

    TestValues values = new TestValues();

    private void GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(CharacterRepository repo) {
        Assertions.assertTrue(repo.getCharacters().right().orElseThrow().contains(values.sheik()));
    }

    @Test
    public void GivenAInMemoryRepoIShouldBeAbleToRetrieveItsCharacters() {
        CharacterInMemoryRepository repo = new CharacterInMemoryRepository(values.characters());
        GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(repo);
    }

    @Test
    public void GivenAScrappingRepoIShouldBeAbleToRetrieveItsCharacters() {
        CharacterScrappingRepository repo = new CharacterScrappingRepository();
        GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(repo);
    }

    @Nested
    class PostgresRepositoryTest {
        HikariDataSource ds = new HikariCP().dsFrom("jdbc:postgresql://localhost/smashtools", "postgres", "postgres");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        Flyway flyway = new FlywayMigrations().flywayFrom(ds);

        @BeforeEach
        public void init() {
            flyway.clean();
            flyway.migrate();
            jdbcTemplate.update("insert into characters values('/sheik', 'sheik')");
        }

        @Test
        public void GivenAPostgresRepoIShouldBeAbleToRetrieveItsCharacters() throws Exception {
            CharacterPostgresRepository repo = new CharacterPostgresRepository(ds);
            GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(repo);
        }
    }
}

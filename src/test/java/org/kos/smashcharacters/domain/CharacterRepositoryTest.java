package org.kos.smashcharacters.domain;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.kos.db.FlywayMigrations;
import org.kos.db.hikari.HikariCP;
import org.kos.db.sql.Sql;
import org.kos.smashcharacters.drivenadapters.memory.CharacterInMemoryRepository;
import org.kos.smashcharacters.drivenadapters.postgres.CharacterPostgresRepository;
import org.kos.smashcharacters.drivenadapters.scrapping.CharacterScrappingRepository;
import org.kos.smashcharacters.helpers.TestValues;

import java.sql.SQLException;


public class CharacterRepositoryTest {

    TestValues values = new TestValues();

    private void GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(CharacterRepository repo) {
        Assertions.assertTrue(repo.getCharacters().right().contains(values.sheik()));
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
        Sql sql = new Sql();
        Flyway flyway = new FlywayMigrations().flywayFrom(ds);

        @BeforeEach
        public void init() throws SQLException {
            flyway.migrate();
            sql.update(ds.getConnection(), "insert into characters values('/sheik', 'sheik')");
        }

        @AfterEach
        public void finish() throws SQLException {
            flyway.clean();
            sql.update(ds.getConnection(), "drop table characters");
        }

        @Test
        public void GivenAPostgresRepoIShouldBeAbleToRetrieveItsCharacters() {
            CharacterPostgresRepository repo = new CharacterPostgresRepository(ds);
            GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(repo);
        }
    }
}

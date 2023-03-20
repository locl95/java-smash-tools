package org.kos.smashcharacters.driveradapters.spring;

import org.kos.db.hikari.HikariCP;
import org.kos.smashcharacters.CharacterProdApp;
import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterApp;
import org.kos.smashcharacters.domain.CharacterMoveRepository;
import org.kos.smashcharacters.domain.CharacterRepository;
import org.kos.smashcharacters.drivenadapters.memory.CharacterMoveInMemoryRepository;
import org.kos.smashcharacters.drivenadapters.postgres.CharacterPostgresRepository;
import org.kos.smashcharacters.drivenadapters.scrapping.CharacterScrappingRepository;
import org.kos.util.either.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class SpringController {

    private final DataSource ds = new HikariCP()
            .dsFrom("jdbc:postgresql://localhost/smashtools", "postgres", "postgres");

    private final CharacterRepository characterCachedRepo = new CharacterPostgresRepository(ds);
    private final CharacterScrappingRepository characterRepo = new CharacterScrappingRepository();
    private final CharacterMoveRepository characterMoveRepository = new CharacterMoveInMemoryRepository(List.of());
    private final CharacterApp characterApp = new CharacterProdApp(characterCachedRepo, characterRepo, characterMoveRepository);

    //TODO: Improve Either so .get is not highlighted

    @GetMapping("/characters")
    ResponseEntity<List<Character>> getCharacters() {
        Either<Exception, List<Character>> res = characterApp.getCharacters();
        if (res.isRight()) return ResponseEntity.ok(res.right().get());
        else return ResponseEntity.internalServerError().build();
    }
}

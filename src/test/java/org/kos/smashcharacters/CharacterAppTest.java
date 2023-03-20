package org.kos.smashcharacters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kos.smashcharacters.domain.CharacterApp;
import org.kos.smashcharacters.drivenadapters.memory.CharacterInMemoryRepository;
import org.kos.smashcharacters.drivenadapters.memory.CharacterMoveInMemoryRepository;
import org.kos.smashcharacters.drivenadapters.scrapping.CharacterScrappingRepository;
import org.kos.smashcharacters.helpers.TestValues;

import java.util.List;

public class CharacterAppTest {

    TestValues values = new TestValues();

    @Test
    public void GivenAnAppICanRetrieveCharacters() {
        CharacterInMemoryRepository charactersRepo = new CharacterInMemoryRepository();
        CharacterScrappingRepository characterScrappingRepository = new CharacterScrappingRepository();
        CharacterMoveInMemoryRepository movesRepo = new CharacterMoveInMemoryRepository(values.characterMoves());
        charactersRepo.setState(values.characters()); //TODO: Set app state instead of repo state

        CharacterApp app = new CharacterProdApp(charactersRepo, characterScrappingRepository, movesRepo);
        Assertions.assertEquals(values.characters(), app.getCharacters().right().get());
    }

    @Test
    public void GivenAnAppICanRetrieveCharacterMoves() {
        CharacterInMemoryRepository charactersRepo = new CharacterInMemoryRepository();
        CharacterScrappingRepository characterScrappingRepository = new CharacterScrappingRepository();
        CharacterMoveInMemoryRepository movesRepo = new CharacterMoveInMemoryRepository(values.characterMoves());

        CharacterApp app = new CharacterProdApp(charactersRepo, characterScrappingRepository, movesRepo);
        Assertions.assertEquals(List.of(values.sheikJab()), app.getCharacterMoves("/sheik"));
    }
}

package org.kos.smashcharacters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kos.smashcharacters.domain.CharacterApp;
import org.kos.smashcharacters.drivenadapters.memory.CharacterInMemoryRepository;
import org.kos.smashcharacters.drivenadapters.memory.CharacterMoveInMemoryRepository;
import org.kos.smashcharacters.helpers.TestValues;

import java.util.List;

public class CharacterAppTest {

    TestValues values = new TestValues();

    @Test
    public void GivenAnAppICanRetrieveCharacters() {
        CharacterInMemoryRepository charactersRepo = new CharacterInMemoryRepository(values.characters());
        CharacterMoveInMemoryRepository movesRepo = new CharacterMoveInMemoryRepository(values.characterMoves());

        CharacterApp app = new CharacterProdApp(charactersRepo, movesRepo);
        Assertions.assertEquals(values.characters(), app.getCharacters().right());
    }

    @Test
    public void GivenAnAppICanRetrieveCharacterMoves() {
        CharacterInMemoryRepository charactersRepo = new CharacterInMemoryRepository(values.characters());
        CharacterMoveInMemoryRepository movesRepo = new CharacterMoveInMemoryRepository(values.characterMoves());

        CharacterApp app = new CharacterProdApp(charactersRepo, movesRepo);
        Assertions.assertEquals(List.of(values.sheikJab()), app.getCharacterMoves("/sheik"));
    }
}

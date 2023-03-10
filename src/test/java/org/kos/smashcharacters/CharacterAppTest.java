package org.kos.smashcharacters;

import org.junit.Assert;
import org.junit.Test;
import org.kos.smashcharacters.domain.CharacterApp;
import org.kos.smashcharacters.drivenadapters.CharacterInMemoryRepository;
import org.kos.smashcharacters.drivenadapters.CharacterMoveInMemoryRepository;
import org.kos.smashcharacters.helpers.TestValues;

import java.util.List;

public class CharacterAppTest {

    TestValues values = new TestValues();

    @Test
    public void GivenAnAppICanRetrieveCharacters() {
        CharacterInMemoryRepository charactersRepo = new CharacterInMemoryRepository(values.characters());
        CharacterMoveInMemoryRepository movesRepo = new CharacterMoveInMemoryRepository(values.characterMoves());

        CharacterApp app = new CharacterProdApp(charactersRepo, movesRepo);
        Assert.assertEquals(values.characters(), app.getCharacters());
    }

    @Test
    public void GivenAnAppICanRetrieveCharacterMoves() {
        CharacterInMemoryRepository charactersRepo = new CharacterInMemoryRepository(values.characters());
        CharacterMoveInMemoryRepository movesRepo = new CharacterMoveInMemoryRepository(values.characterMoves());

        CharacterApp app = new CharacterProdApp(charactersRepo, movesRepo);
        Assert.assertEquals(List.of(values.sheikJab()), app.getCharacterMoves("sheik"));
    }
}

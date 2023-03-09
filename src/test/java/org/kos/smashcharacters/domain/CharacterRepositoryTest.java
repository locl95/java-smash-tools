package org.kos.smashcharacters.domain;

import org.junit.Assert;
import org.junit.Test;
import org.kos.smashcharacters.drivenadapters.CharacterInMemoryRepository;
import org.kos.smashcharacters.helpers.TestValues;


public class CharacterRepositoryTest {

    TestValues values = new TestValues();

    private void GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(CharacterRepository repo) {
        Assert.assertEquals(values.characters(), repo.getCharacters());
    }

    @Test
    public void GivenAInMemoryRepoIShouldBeAbleToRetrieveItsCharacters() {
        CharacterInMemoryRepository repo = new CharacterInMemoryRepository(values.characters());
        GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(repo);
    }
}

package org.kos.smashcharacters.drivenadapters.memory;

import org.junit.jupiter.api.Test;
import org.kos.smashcharacters.domain.CharacterRepositoryTest;

public class CharacterInMemoryRepositoryTest implements CharacterRepositoryTest {

    @Override
    @Test
    public void GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters() {
        CharacterInMemoryRepository repo = new CharacterInMemoryRepository();
        _GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(repo);
    }

    @Override
    @Test
    public void GivenACharacterRepositoryIShouldBeAbleToInsertCharacters() {
        CharacterInMemoryRepository repo = new CharacterInMemoryRepository();
        _GivenACharacterRepositoryIShouldBeAbleToInsertCharacters(repo);
    }

    @Override
    @Test
    public void GivenACharacterRepositoryIShouldNotBeAbleToInsertDuplicatedCharacters() {
        CharacterInMemoryRepository repo = new CharacterInMemoryRepository();
        _GivenACharacterRepositoryIShouldNotBeAbleToInsertDuplicatedCharacters(repo);
    }
}

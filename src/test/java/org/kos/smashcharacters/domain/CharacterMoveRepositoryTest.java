package org.kos.smashcharacters.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kos.smashcharacters.drivenadapters.memory.CharacterMoveInMemoryRepository;
import org.kos.smashcharacters.drivenadapters.scrapping.CharacterMoveScrappingRepository;
import org.kos.smashcharacters.helpers.TestValues;

public class CharacterMoveRepositoryTest {

    TestValues values = new TestValues();

    private void GivenACharacterRepositoryAndACharacterNameIShouldBeAbleToRetrieveItsMoves(CharacterMoveRepository repo) {
        Assertions.assertTrue(repo.getCharacterMoves("/sheik").contains(values.sheikJab()));
    }

    @Test
    public void GivenAnInMemoryRepoIShouldBeAbleToRetrieveItsCharacterMoves() {
        CharacterMoveInMemoryRepository repo = new CharacterMoveInMemoryRepository(values.characterMoves());
        GivenACharacterRepositoryAndACharacterNameIShouldBeAbleToRetrieveItsMoves(repo);
    }

    @Test
    public void GivenAnScrapperRepoIShouldBeAbleToRetrieveItsCharacterMoves() {
        CharacterMoveScrappingRepository repo = new CharacterMoveScrappingRepository();
        GivenACharacterRepositoryAndACharacterNameIShouldBeAbleToRetrieveItsMoves(repo);
    }
}

package org.kos.smashcharacters.domain;

import org.junit.Assert;
import org.junit.Test;
import org.kos.smashcharacters.drivenadapters.CharacterMoveInMemoryRepository;
import org.kos.smashcharacters.drivenadapters.CharacterMoveScrappingRepository;
import org.kos.smashcharacters.helpers.TestValues;

import java.util.List;

public class CharacterMoveRepositoryTest {

    TestValues values = new TestValues();

    private void GivenACharacterRepositoryAndACharacterNameIShouldBeAbleToRetrieveItsMoves(CharacterMoveRepository repo) {
        Assert.assertTrue(repo.getCharacterMoves("/sheik").contains(values.sheikJab()));
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

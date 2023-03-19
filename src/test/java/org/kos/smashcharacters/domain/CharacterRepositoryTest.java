package org.kos.smashcharacters.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kos.smashcharacters.helpers.TestValues;
import org.kos.util.either.Right;

import java.util.List;

public interface CharacterRepositoryTest {
    TestValues values = new TestValues();

    default void _GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(CharacterRepository repo) {
        List<Character> initialState = List.of(values.sheik());
        repo.setState(initialState);
        Assertions.assertTrue(repo.getCharacters().right().orElseThrow().contains(values.sheik()));
        Assertions.assertEquals(initialState, repo.getState());
    }

    default void _GivenACharacterRepositoryIShouldBeAbleToInsertCharacters(CharacterRepository repo) {
        List<Character> expectedState = List.of(values.sheik());
        Assertions.assertEquals(new Right<>(1), repo.insertCharacter(values.sheik()));
        Assertions.assertEquals(expectedState, repo.getState());
    }

    @Test
    void GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters();

    @Test
    void GivenACharacterRepositoryIShouldBeAbleToInsertCharacters();
}

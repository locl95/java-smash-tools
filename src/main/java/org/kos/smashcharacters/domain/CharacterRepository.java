package org.kos.smashcharacters.domain;

import org.kos.util.either.Either;

import java.util.List;

public interface CharacterRepository extends CharacterRepositoryState {
    Either<Exception, List<Character>> getCharacters();

    Either<Exception, Integer> insertCharacter(Character character);
}

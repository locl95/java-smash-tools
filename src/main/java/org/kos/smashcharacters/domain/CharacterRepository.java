package org.kos.smashcharacters.domain;

import org.kos.smashcharacters.domain.errors.character.CharacterError;
import org.kos.util.either.Either;

import java.util.List;

public interface CharacterRepository extends CharacterRepositoryState {
    Either<Exception, List<Character>> getCharacters();

    Either<CharacterError, Integer> insertCharacter(Character character);
}

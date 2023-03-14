package org.kos.smashcharacters.domain;

import org.kos.util.either.Either;

import java.util.List;

public interface CharacterRepository {
    Either<Exception, List<Character>> getCharacters();
}

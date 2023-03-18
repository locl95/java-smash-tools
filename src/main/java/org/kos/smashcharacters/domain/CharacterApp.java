package org.kos.smashcharacters.domain;


import org.kos.util.either.Either;

import java.util.List;

public interface CharacterApp {
    Either<Exception, List<Character>> getCharacters();

    List<CharacterMove> getCharacterMoves(String characterSlug);
}

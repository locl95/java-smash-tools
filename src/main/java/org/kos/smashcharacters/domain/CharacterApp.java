package org.kos.smashcharacters.domain;


import org.kos.util.either.Either;

import java.util.List;

//TODO: Make it have it's own state, same as repositories

public interface CharacterApp {
    Either<Exception, List<Character>> getCharacters();

    List<CharacterMove> getCharacterMoves(String characterSlug);
}

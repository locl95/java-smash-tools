package org.kos.smashcharacters.domain;

import java.util.List;

public interface CharacterApp {
    List<Character> getCharacters();

    List<CharacterMove> getCharacterMoves(String characterSlug);
}

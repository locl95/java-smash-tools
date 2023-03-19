package org.kos.smashcharacters.domain;

import java.util.List;

//TODO: Make it have it's own state, same as CharacterRepository

public interface CharacterMoveRepository {
    List<CharacterMove> getCharacterMoves(String characterSlug);
}

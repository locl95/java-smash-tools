package org.kos.smashcharacters.domain;

import java.util.List;

public interface CharacterMoveRepository {
    List<CharacterMove> getCharacterMoves(String characterSlug);
}

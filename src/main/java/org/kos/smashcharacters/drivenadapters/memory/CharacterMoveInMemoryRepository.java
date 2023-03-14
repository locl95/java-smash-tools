package org.kos.smashcharacters.drivenadapters.memory;

import org.kos.smashcharacters.domain.CharacterMove;
import org.kos.smashcharacters.domain.CharacterMoveRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CharacterMoveInMemoryRepository implements CharacterMoveRepository {
    List<CharacterMove> characterMoves;

    public CharacterMoveInMemoryRepository(List<CharacterMove> characterMoves) {
        this.characterMoves = characterMoves;
    }

    @Override
    public List<CharacterMove> getCharacterMoves(String characterSlug) {
        return characterMoves.stream().filter(cm -> cm.characterSlug().equals(characterSlug)).collect(Collectors.toList());
    }
}

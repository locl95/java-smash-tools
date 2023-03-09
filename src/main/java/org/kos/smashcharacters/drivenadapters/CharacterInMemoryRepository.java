package org.kos.smashcharacters.drivenadapters;

import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterRepository;

import java.util.List;

public class CharacterInMemoryRepository implements CharacterRepository {
    List<Character> characters;

    public CharacterInMemoryRepository(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public List<Character> getCharacters() {
        return characters;
    }
}

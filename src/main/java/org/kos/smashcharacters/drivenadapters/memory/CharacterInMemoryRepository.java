package org.kos.smashcharacters.drivenadapters.memory;

import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterRepository;
import org.kos.util.either.Either;
import org.kos.util.either.Right;

import java.util.List;

public class CharacterInMemoryRepository implements CharacterRepository {
    List<Character> characters;

    public CharacterInMemoryRepository(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public Either<Exception, List<Character>> getCharacters() {
        return new Right<>(characters);
    }
}

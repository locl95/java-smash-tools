package org.kos.smashcharacters.drivenadapters.memory;

import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterRepository;
import org.kos.smashcharacters.domain.errors.character.CharacterDuplicated;
import org.kos.smashcharacters.domain.errors.character.CharacterError;
import org.kos.util.either.Either;
import org.kos.util.either.Left;
import org.kos.util.either.Right;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterInMemoryRepository implements CharacterRepository {
    List<Character> characters;

    public CharacterInMemoryRepository() {
        this.characters = List.of();
    }

    @Override
    public Either<Exception, List<Character>> getCharacters() {
        return new Right<>(characters);
    }

    @Override
    public Either<CharacterError, Integer> insertCharacter(Character character) {
        if (characters.contains(character)) return new Left<>(new CharacterDuplicated(character.slug()));
        this.characters = Stream.concat(this.characters.stream(), Stream.of(character)).toList();
        return new Right<>(1);
    }

    @Override
    public void setState(List<Character> characters) {
        this.characters = Stream.concat(this.characters.stream(), characters.stream()).toList();
    }

    @Override
    public List<Character> getState() {
        return characters;
    }
}

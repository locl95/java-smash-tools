package org.kos.smashcharacters;

import org.kos.smashcharacters.domain.*;
import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.drivenadapters.scrapping.CharacterScrappingRepository;
import org.kos.util.either.Either;

import java.util.List;

public class CharacterProdApp implements CharacterApp {

    private final CharacterRepository cacheCharacterRepository;

    private final CharacterScrappingRepository characterScrappingRepository;
    private final CharacterMoveRepository characterMoveRepository;

    public CharacterProdApp(CharacterRepository cacheCharacterRepository, CharacterScrappingRepository characterScrappingRepository, CharacterMoveRepository characterMoveRepository) {
        this.cacheCharacterRepository = cacheCharacterRepository;
        this.characterScrappingRepository = characterScrappingRepository;
        this.characterMoveRepository = characterMoveRepository;
    }

    //TODO: Improve Either so this function is easier to read and write

    @Override
    public Either<Exception, List<Character>> getCharacters() {
        Either<Exception, List<Character>> cachedResult = cacheCharacterRepository.getCharacters();
        if (cachedResult.isLeft() || cachedResult.right().isEmpty()) {
            Either<Exception, List<Character>> scrappedResult = characterScrappingRepository.getCharacters();
            scrappedResult.right().get().forEach(cacheCharacterRepository::insertCharacter);
            return scrappedResult;
        }
        if (cachedResult.right().isPresent() && cachedResult.right().get().isEmpty()) {
            Either<Exception, List<Character>> scrappedResult = characterScrappingRepository.getCharacters();
            scrappedResult.right().get().forEach(cacheCharacterRepository::insertCharacter);
            return scrappedResult;
        }
        return cachedResult;
    }

    @Override
    public List<CharacterMove> getCharacterMoves(String characterSlug) {
        return characterMoveRepository.getCharacterMoves(characterSlug);
    }
}

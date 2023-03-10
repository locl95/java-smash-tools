package org.kos.smashcharacters;

import org.kos.smashcharacters.domain.*;
import org.kos.smashcharacters.domain.Character;

import java.util.List;

public class CharacterProdApp implements CharacterApp {

    CharacterRepository characterRepository;
    CharacterMoveRepository characterMoveRepository;

    public CharacterProdApp(CharacterRepository characterRepository, CharacterMoveRepository characterMoveRepository) {
        this.characterRepository = characterRepository;
        this.characterMoveRepository = characterMoveRepository;
    }

    @Override
    public List<Character> getCharacters() {
        return characterRepository.getCharacters();
    }

    @Override
    public List<CharacterMove> getCharacterMoves(String characterSlug) {
        return characterMoveRepository.getCharacterMoves(characterSlug);
    }
}

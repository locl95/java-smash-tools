package org.kos.smashcharacters.helpers;

import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterMove;

import java.util.List;

public class TestValues {
    Character sheik;
    CharacterMove sheikJab;
    List<CharacterMove> characterMoves;
    List<Character> characters;

    public TestValues() {
        this.sheik = new Character("/sheik", "sheik");
        this.sheikJab = new CharacterMove("jab 1", "/sheik");

        this.characterMoves = List.of(
                sheikJab,
                new CharacterMove("jab 2", "/bayonetta"));
        this.characters = List.of(sheik);
    }

    public CharacterMove sheikJab() {
        return sheikJab;
    }

    public List<CharacterMove> characterMoves() {
        return characterMoves;
    }

    public List<Character> characters() {
        return characters;
    }

    public Character sheik() {
        return sheik;
    }
}

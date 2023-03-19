package org.kos.smashcharacters.domain;

import java.util.List;

interface CharacterRepositoryState {
    void setState(List<Character> characters);

    List<Character> getState();
}

package org.kos.smashcharacters.domain.errors.character;

public record CharacterDuplicated(String slug) implements CharacterError {

}

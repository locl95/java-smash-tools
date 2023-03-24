package org.kos.smashcharacters.domain.errors.character;

public record CharacterUnexpectedError(String error) implements CharacterError {
}

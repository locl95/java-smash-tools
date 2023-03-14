package org.kos.smashcharacters.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kos.smashcharacters.helpers.TestValues;

public class CharacterMoveTest {

    TestValues values = new TestValues();

    @Test
    public void GivenACharacterMoveIShouldBeAbleToRetrieveItsName() {
        Assertions.assertEquals("jab 1", values.sheikJab().name());
    }
}

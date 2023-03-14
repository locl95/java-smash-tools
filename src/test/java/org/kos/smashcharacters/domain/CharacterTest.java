package org.kos.smashcharacters.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kos.smashcharacters.helpers.TestValues;

public class CharacterTest {

    TestValues values = new TestValues();

    @Test
    public void GivenACharacterIShouldBeAbleToRetrieveItsName() {
        Assertions.assertEquals("sheik", values.sheik().name());
    }
}

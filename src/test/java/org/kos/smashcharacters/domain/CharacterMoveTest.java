package org.kos.smashcharacters.domain;

import org.junit.Assert;
import org.junit.Test;
import org.kos.smashcharacters.helpers.TestValues;

public class CharacterMoveTest {

    TestValues values = new TestValues();

    @Test
    public void GivenACharacterMoveIShouldBeAbleToRetrieveItsName() {
        Assert.assertEquals("Jab 1", values.sheikJab().name());
    }
}

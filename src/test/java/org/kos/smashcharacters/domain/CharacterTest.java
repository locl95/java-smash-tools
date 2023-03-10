package org.kos.smashcharacters.domain;

import org.junit.Assert;
import org.junit.Test;
import org.kos.smashcharacters.helpers.TestValues;

public class CharacterTest {

    TestValues values = new TestValues();

    @Test
    public void GivenACharacterIShouldBeAbleToRetrieveItsName() {
        Assert.assertEquals("sheik", values.sheik().name());
    }
}

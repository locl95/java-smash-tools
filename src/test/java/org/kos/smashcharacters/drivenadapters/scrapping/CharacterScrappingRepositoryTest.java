package org.kos.smashcharacters.drivenadapters.scrapping;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class CharacterScrappingRepositoryTest {

    @Disabled("Disabled until Scrapping Repo design is reviewed!")
    @Test
    public void GivenAScrappingRepoIShouldBeAbleToRetrieveItsCharacters() {
        //TODO: It's actually the same behaviour as get characters from characters repositories but this one should not implement insert ...
        CharacterScrappingRepository repo = new CharacterScrappingRepository();
        //GivenACharacterRepositoryIShouldBeAbleToRetrieveItsCharacters(repo);
    }

}

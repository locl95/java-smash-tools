package org.kos.smashcharacters.drivenadapters.scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterMove;
import org.kos.smashcharacters.domain.CharacterMoveRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CharacterMoveScrappingRepository implements CharacterMoveRepository {
    @Override
    public List<CharacterMove> getCharacterMoves(String characterSlug) {
        try {
            Document doc = Jsoup.connect("https://ultimateframedata.com" + characterSlug).get();
            Element elementsById = doc
                    .body()
                    .getElementById("contentcontainer");

            Optional<Element> maybeCharList = elementsById == null ? Optional.empty() : Optional.of(elementsById);

            return maybeCharList.map(element -> element
                            .getElementsByClass("moves")
                            .stream()
                            .flatMap(elem -> elem.getElementsByClass("movecontainer")
                                    .stream().flatMap(foo -> foo.getElementsByClass("movename").eachText().stream()))
                            .map(s -> new CharacterMove(s.toLowerCase(), characterSlug)).toList())
                    .orElseGet(List::of);
        } catch (IOException e) {
            return List.of();
        }
    }
}

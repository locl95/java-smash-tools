package org.kos.smashcharacters.drivenadapters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CharacterScrappingRepository implements CharacterRepository {

    @Override
    public List<Character> getCharacters() {
        try {
            Document doc = Jsoup.connect("https://ultimateframedata.com").get();
            Element elementsById = doc
                    .body()
                    .getElementById("charList");

            Optional<Element> maybeCharList = elementsById == null ? Optional.empty() : Optional.of(elementsById);

            return maybeCharList.map(element -> element
                    .getElementsByClass("charactericon")
                    .next()
                    .select("a")
                    .eachAttr("href")
                    .stream()
                    .map(s -> new Character(s, s.replace("/", "").replace("_", " ")))
                    .toList()).orElseGet(List::of);
        } catch (IOException e) {
            return List.of();
        }
    }
}

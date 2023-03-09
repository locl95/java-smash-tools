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
            Document doc = Jsoup.connect("https://ultimateframedata.com/").get();
            Element elementsByClass = doc
                    .body()
                    .getElementById("charList");

            Optional<Element> foo = elementsByClass == null ? Optional.empty() : Optional.of(elementsByClass);

            return foo.map(element -> element
                    .getElementsByClass("charactericon")
                    .next()
                    .select("a")
                    .eachAttr("href")
                    .stream().map(s -> s.replace("/", ""))
                    .map(s -> s.replace("_", " "))
                    .map(Character::new)
                    .toList()).orElseGet(List::of);
        } catch (IOException e) {
            return List.of();
        }
    }
}

package org.kos.smashcharacters.drivenadapters.scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.kos.smashcharacters.domain.Character;
import org.kos.smashcharacters.domain.CharacterRepository;
import org.kos.util.either.Either;
import org.kos.util.either.Left;
import org.kos.util.either.Right;

import java.io.IOException;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;

public class CharacterScrappingRepository implements CharacterRepository {

    @Override
    public Either<Exception, List<Character>> getCharacters() {
        try {
            Document doc = Jsoup.connect("https://ultimateframedata.com").get();
            Element elementsById = doc
                    .body()
                    .getElementById("charList");

            Optional<Element> maybeCharList = elementsById == null ? Optional.empty() : Optional.of(elementsById);

            Optional<List<Character>> maybeCharacters = maybeCharList.map(element -> element
                    .getElementsByClass("charactericon")
                    .next()
                    .select("a")
                    .eachAttr("href")
                    .stream()
                    .map(s -> new Character(s, s.replace("/", "").replace("_", " ")))
                    .toList());
            if (maybeCharacters.isPresent()) return new Right<>(maybeCharacters.get());
            else return new Left<>(new Exception("Couldn't find characters in https://ultimateframedata.com"));

        } catch (IOException e) {
            return new Left<>(e);
        }
    }
}

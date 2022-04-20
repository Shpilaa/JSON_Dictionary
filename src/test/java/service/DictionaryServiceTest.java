package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;


class DictionaryServiceTest {
    String word = "oui";
    Map<String, List<String>> langMap  = new HashMap<String, List<String>>() {{
        put("English", Arrays.asList("a", "b", "and", "hi", "tree"));
        put("French", Arrays.asList("oui", "bonjour", "merci"));
    }};

    @Test
    void readFromDir() throws IOException {
        HashMap<String, List<String>> testMap  = new HashMap<String, List<String>>() {{
            put("English", Arrays.asList("a", "abandon"));
        }};
        assertEquals(testMap, DictionaryService.readFromDir("src/test/test_db"));
    }

    @Test
    void wordSearch() throws IOException {
        List<String> langNameList = Arrays.asList("English");
        assertEquals(langNameList, DictionaryService.wordSearch("hi", langMap));
    }
}
package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.WordList;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DictionaryService {
    // Method loading all JSON files from db folder to a map

    public static Map<String, List<String>> readFromDir(String folderPath) throws IOException {

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        Map<String, List<String>> tmpWordMap = new HashMap<>();

        Arrays.stream(listOfFiles).forEach(file -> {
            if (file.isFile()) {
                ObjectMapper objectMapper = new ObjectMapper();
                WordList wordlist = null;
                try {
                    wordlist = objectMapper.readValue(new File(folderPath + "/" + file.getName()), WordList.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tmpWordMap.put(wordlist.language, wordlist.words);
            }
        });

        return tmpWordMap;
    }

    // Method looking up all words from the map

    public static List<String> wordSearch(String wordToSearch, Map<String, List<String>> dictionaryMap) throws IOException {
        List<String> langList = new ArrayList<>();
        dictionaryMap
                .forEach((key, value) -> {
                    if (value.stream().anyMatch(word -> word.equals(wordToSearch))) {
                        langList.add(key);
                    }
                });

        if (!langList.isEmpty()) {
            String langResult = "Word \"" + wordToSearch + "\" comes from those languages: ";
            langResult += ParseService.listParser(langList);
            langResult += "\n";
            System.out.println(langResult);
        } else {
            System.out.println("Sorry, we couldn't find the word you are looking for in our database.\n");
        }
        return langList;
    }

    // Method creating new JSON file with

    public static void createJSON(String key, List<String> values) throws IOException {
        JSONObject jsonFile = new JSONObject();
        jsonFile.put("words", values);
        jsonFile.put("language", key);
        FileWriter file = new FileWriter("src/main/resources/dictionary_db/" + key + ".json");
        file.write(jsonFile.toJSONString());
        file.close();
    }
}
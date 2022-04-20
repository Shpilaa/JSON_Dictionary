package service;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    static public void menu() throws IOException, DocumentException {
        int countWords = 0;
        List<String> listLanguages = new ArrayList<>();
        String folderPath = "src/main/resources/dictionary_db";
        while (true) {
            // Displaying menu and getting user input
            System.out.println("Please enter a number or write 'exit' to quit.");
            System.out.println("1. Search what language does the word come from.");
            System.out.println("2. Add a new Dictionary to database");
            Scanner commandScan = new Scanner(System.in);
            String command = commandScan.nextLine();
            if (command.equals("1")) {
                // Looking up a word
                System.out.print("Please enter a word: ");
                String wordToLook = commandScan.nextLine();
                countWords++;
                PdfService.addLanguages(listLanguages, DictionaryService.wordSearch(wordToLook, DictionaryService.readFromDir(folderPath)));
            } else if (command.equals("2")) {
                // Creating file
                System.out.print("Please enter a language: ");
                String language = commandScan.nextLine();
                System.out.println("Enter words from this language (seperate using space):");
                String words = commandScan.nextLine();
                List<String> wordList = Arrays.asList(words.split(" ", -1));
                DictionaryService.createJSON(language, wordList);
            } else if (command.equals("exit")) {
                break;
            }
        }
        PdfService.createPDF(countWords, listLanguages);
    }
}

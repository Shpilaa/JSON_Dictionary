package service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PdfServiceTest {

    List<String> list1 = new ArrayList<>();

    @Test
    void addLanguages() {
        List<String> list2 = Arrays.asList("English");
        List<String> list3 = Arrays.asList("English", "French");
        List<String> list4 = Arrays.asList("English", "English");
        PdfService.addLanguages(list1, list2);
        PdfService.addLanguages(list1, list3);
        PdfService.addLanguages(list1, list4);
        assertEquals(list1, Arrays.asList("English", "French"));
    }
}
package service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParseServiceTest {

    @Test
    void listParser() {
        List<String> tmpLangList = Arrays.asList("French", "English");
        assertEquals(ParseService.listParser(tmpLangList), "French, English");
    }
}
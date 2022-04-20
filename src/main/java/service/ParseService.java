package service;

import java.util.List;
import java.util.stream.Collectors;

public class ParseService {
    public static String listParser(List<String> listToParse){
        return listToParse.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}

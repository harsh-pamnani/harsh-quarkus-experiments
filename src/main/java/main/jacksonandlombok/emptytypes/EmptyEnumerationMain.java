package main.jacksonandlombok.emptytypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Enumeration;

public class EmptyEnumerationMain {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new EmptyEnumerationAdapterModule());

        String result1 = objectMapper.writeValueAsString(Collections.emptyEnumeration());
        System.out.println(result1); // {}

        Enumeration<String> result2 = objectMapper.readValue("{}", Enumeration.class);
        System.out.println(result2);
    }
}

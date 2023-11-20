package main.jacksonAndLombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Person1 p1 = mapper.readValue("{\"name\": \"harsh\"}", Person1.class);
        System.out.println(p1);
        Person2 p2 = mapper.readValue("{\"name\": \"harsh\"}", Person2.class);
        System.out.println(p2);
        System.out.println(p2.age().orElse(5));
    }
}

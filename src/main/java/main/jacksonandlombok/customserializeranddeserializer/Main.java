package main.jacksonandlombok.customserializeranddeserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.registerModule(new JSR310Module()); // Java 8 date/time type `java.time.LocalDateTime` not supported by default: so added JSR310Module module to enable handling

        // Serialization
        LocalDateTime localDateTime = LocalDateTime.of(2021, 12, 13, 9, 15, 53, 810000000);
        String json = objectMapper1.writeValueAsString(localDateTime);
        System.out.println("Serialization WITHOUT custom serializer: " + json);

        // Deserialization
        String expectedJson = "[2021,12,13,9,15,53,810000000]";
        LocalDateTime localDateTimeFromJson = objectMapper1.readValue(expectedJson, LocalDateTime.class);
        System.out.println("Deserialization WITHOUT custom deserializer: " + localDateTimeFromJson);



        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.registerModule(new JacksonModule());

        // Serialization
        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 12, 13, 9, 15, 53, 810000000);
        String json2 = objectMapper2.writeValueAsString(localDateTime2);
        System.out.println("Serialization WITH custom serializer: " + json2);

        // Deserialization
        String expectedJson2 = "{\"date\":{\"year\":2021,\"month\":12,\"day\":13},\"time\":{\"hour\":9,\"minute\":15,\"second\":53,\"nano\":810000000}}";
        LocalDateTime localDateTimeFromJson2 = objectMapper2.readValue(expectedJson2, LocalDateTime.class);
        System.out.println("Deserialization WITH custom deserializer: " + localDateTimeFromJson2);
    }
}

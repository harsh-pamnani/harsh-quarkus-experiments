package main.jacksonandlombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class PersonMain {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Person1 p1 = mapper.readValue("{\"name\": \"harsh\"}", Person1.class);
        System.out.println(p1); // Person1 has @Builder.Default for age. So, it will generate empty Optional

        mapper.registerModule(new Jdk8Module());
        Person2 p2 = mapper.readValue("{\"name\": \"harsh\"}", Person2.class);
        System.out.println(p2);// Person2 is a record. So, by default it will generate `null` for age. but we have registered Jdk8Module. So, it will generate empty Optional
    }
}

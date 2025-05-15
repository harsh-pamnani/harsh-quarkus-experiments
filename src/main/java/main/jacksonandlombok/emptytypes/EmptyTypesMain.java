package main.jacksonandlombok.emptytypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmptyTypesMain {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String result1 = objectMapper.writeValueAsString(Collections.emptyList());
        System.out.println(result1); // []

        String result2 = objectMapper.writeValueAsString(Collections.emptySet());
        System.out.println(result2); // []

        String result3 = objectMapper.writeValueAsString(Collections.emptyMap());
        System.out.println(result3); // {}

        //        NOT WORKING
        //        String result4 = objectMapper.writeValueAsString(Collections.emptyEnumeration());
        //        System.out.println(result4); // {}

        List<String> result5 = objectMapper.readValue("[]", List.class);
        System.out.println(result5);

        Set<String> result6 = objectMapper.readValue("[]", Set.class);
        System.out.println(result6);

        Map<String, String> result7 = objectMapper.readValue("{}", Map.class);
        System.out.println(result7);

        //        NOT WORKING
        //        Enumeration<String> result8 = objectMapper.readValue("{}", Enumeration.class);
        //        System.out.println(result8);
    }
}

package main.jsoncreatorjsonproperty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.util.Optional;

public class JsonMain {
    public static void main(String[] args) {

        String json = "{\"id\":1,\"name\":\"Harsh\"}";
        String jsonWithDifferentFieldName = "{\"id\":2,\"firstName\":\"Jai\"}";

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new Jdk8Module());

        try {
            Employee1 e1 = objectMapper.readValue(json, Employee1.class);
            System.out.println(e1);

            // This will throw an exception if there is no-args constructor is not present
            // Comment the no-args constructor in Employee2 to see the exception
            Employee2 e2 = objectMapper.readValue(json, Employee2.class);
            System.out.println(e2);

            EmployeeWithCreator e3 = objectMapper.readValue(jsonWithDifferentFieldName, EmployeeWithCreator.class);
            System.out.println(e3);

            ErrorResponseBodyWithType ex1 = new ErrorResponseBodyWithType("AAA", "something went wrong 1", "TYPE_1", Optional.of(1));
            ErrorResponseBodyWithType ex2 = new ErrorResponseBodyWithType("BBB", "something went wrong 2", null, Optional.ofNullable(null));

            // Since "ex2" has `type` as null. So if we have set "@JsonInclude(Include.NON_NULL)" for the class, it will have only "error" and "message" in the JSON.
            // Also, "ex2" has `discount` as optional null, and we have set "@JsonInclude(Include.NON_ABSENT)" for the class, it will have only "error" and "message" in the JSON.
            System.out.println(objectMapper.writeValueAsString(ex1));
            System.out.println(objectMapper.writeValueAsString(ex2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

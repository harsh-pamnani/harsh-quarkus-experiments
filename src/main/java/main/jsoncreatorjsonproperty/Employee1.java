package main.jsoncreatorjsonproperty;


import com.fasterxml.jackson.annotation.JsonProperty;

public record Employee1(Integer id,
                        String name) {
}
//public record Employee1(@JsonProperty("id") Integer id,
//                        @JsonProperty("firstName") String name) {
//}

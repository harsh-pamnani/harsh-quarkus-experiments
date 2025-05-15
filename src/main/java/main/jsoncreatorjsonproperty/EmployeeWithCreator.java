package main.jsoncreatorjsonproperty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class EmployeeWithCreator {
    public final Integer id;
    public final String name;

    @JsonCreator
    public EmployeeWithCreator(@JsonProperty("id") Integer id, @JsonProperty("firstName") String name) {
        this.id = id;
        this.name = name;
    }
}

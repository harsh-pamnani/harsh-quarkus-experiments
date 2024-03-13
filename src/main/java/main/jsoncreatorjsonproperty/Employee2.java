package main.jsoncreatorjsonproperty;

import lombok.ToString;

@ToString
public class Employee2 {
    public final Integer id;
    public final String name;

    public Employee2() {
        this(null, null);
    }

    public Employee2(Integer id,
                     String name) {
        this.id = id;
        this.name = name;
    }
}

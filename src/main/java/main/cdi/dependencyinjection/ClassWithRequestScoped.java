package main.cdi.dependencyinjection;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ClassWithRequestScoped {
    private static int count = 0;

    @PostConstruct
    public void increaseCount() {
        count++;
    }

    public String greetUser(String name) {
        return "Hello, " + name + "! " + count + " instances of ClassWithRequestScoped class have been created.";
    }
}

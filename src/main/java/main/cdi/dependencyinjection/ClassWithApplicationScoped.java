package main.cdi.dependencyinjection;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClassWithApplicationScoped {
    private static int count = 0;

    @PostConstruct
    public void increaseCount() {
        count++;
    }

    public String greetUser(String name) {
        return "Hello, " + name + "! " + count + " instances of this class have been created.";
    }
}
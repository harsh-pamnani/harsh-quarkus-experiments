package main.cdi.dependencyinjection;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

@Singleton
public class ClassWithSingletonScoped {
    private static int count = 0;

    @PostConstruct
    public void increaseCount() {
        count++;
    }

    public String greetUser(String name) {
        return "Hello, " + name + "! " + count + " instances of ClassWithSingletonScoped class have been created.";
    }
}
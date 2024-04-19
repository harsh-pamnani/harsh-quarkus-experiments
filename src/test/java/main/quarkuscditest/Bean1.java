package main.quarkuscditest;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Bean1 {
    public String greet(String name) {
        return "Actual greeting message for " + name;
    }
}

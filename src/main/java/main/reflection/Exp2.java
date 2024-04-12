package main.reflection;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record Exp2(String name) {
    public static String greetings() {
        return "Hello from Exp2";
    }
}

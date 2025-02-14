package main.concepts.autoclosable;

public class MySampleClass implements AutoCloseable {
    @Override
    public void close() {
        System.out.println("Resource closed!");
    }

    public void useResource() {
        System.out.println("Using the resource...");
    }
}

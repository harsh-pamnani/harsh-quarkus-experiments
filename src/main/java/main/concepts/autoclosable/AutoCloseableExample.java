package main.concepts.autoclosable;

public class AutoCloseableExample {
    public static void main(String[] args) {
        // Using try-with-resources to automatically close the resource
        try (MySampleClass resource1 = new MySampleClass()) {
            resource1.useResource();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        // Using `finally` block to close the resource
        MySampleClass resource2 = new MySampleClass();
        try {
            resource2.useResource();
        } finally {
            resource2.close();
        }

        // IDE will throw Use try-with-resources or close this "MySampleClass" in a "finally" clause.
        MySampleClass resource3 = new MySampleClass();
        resource3.useResource();
    }
}

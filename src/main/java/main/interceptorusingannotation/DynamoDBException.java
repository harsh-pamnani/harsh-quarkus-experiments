package main.interceptorusingannotation;

public class DynamoDBException extends RuntimeException {
    public DynamoDBException(String message) {
        super(message);
    }
}

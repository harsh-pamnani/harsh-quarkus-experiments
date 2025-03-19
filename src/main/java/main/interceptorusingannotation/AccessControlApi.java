package main.interceptorusingannotation;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccessControlApi {
    public void someMethodThrowingSecretDbError() {
        throw new DynamoDBException("Error exposing the DB schema");
    }
}

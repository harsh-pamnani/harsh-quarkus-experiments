package main.reflectionforjackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

@Singleton
public class ObjectMapperProducer {

    @Produces
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
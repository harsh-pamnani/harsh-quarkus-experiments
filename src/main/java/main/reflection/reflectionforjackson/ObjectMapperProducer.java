package main.reflection.reflectionforjackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import main.jacksonandlombok.customserializeranddeserializer.JacksonModule;

@Singleton
public class ObjectMapperProducer {

    @Produces
    ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new Jdk8Module()).registerModule(new JacksonModule());
    }
}

package main.redis;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public record CreateAuthorDto(String name,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") Date dob,
                              String nationality) {
    public Author toDomain() {
        return new Author(UUID.randomUUID().toString(),
                          name,
                          dob,
                          nationality);
    }
}

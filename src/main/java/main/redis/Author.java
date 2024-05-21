package main.redis;

import java.util.Date;

public record Author(String id,
                     String name,
                     Date dob,
                     String nationality) {
}

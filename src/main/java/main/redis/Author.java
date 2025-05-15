package main.redis;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public record Author(
        String id,
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") Date dob,
        CountryCode nationality) {}

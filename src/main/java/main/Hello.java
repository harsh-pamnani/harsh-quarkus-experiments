package main;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class Hello {
    public static void main(String[] args) {
        Duration duration = Duration.ofMinutes(5L);
        System.out.println(duration.toMinutesPart());
    }
}


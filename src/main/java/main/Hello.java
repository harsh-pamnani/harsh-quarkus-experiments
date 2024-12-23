package main;

import java.time.Duration;

public class Hello {
    public static void main(String[] args) {
        Duration duration = Duration.ofMinutes(5L);
        System.out.println(duration.toMinutesPart());
    }
}


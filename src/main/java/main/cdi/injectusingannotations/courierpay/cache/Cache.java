package main.cdi.injectusingannotations.courierpay.cache;

import java.time.Duration;
import java.util.Map;

public interface Cache<S, T> {
    Duration EXPIRATION = Duration.ofMinutes(10);

    void set(Map<S, T> settings);

    default Duration getExpirationDuration() {
        return EXPIRATION;
    }
}

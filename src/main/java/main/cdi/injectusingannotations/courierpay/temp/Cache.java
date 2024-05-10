package main.cdi.injectusingannotations.courierpay.temp;

import java.time.Duration;
import java.util.Map;

public interface Cache<S, T> {
    void set(Map<S, T> settings);

    Duration getExpirationDuration();
}

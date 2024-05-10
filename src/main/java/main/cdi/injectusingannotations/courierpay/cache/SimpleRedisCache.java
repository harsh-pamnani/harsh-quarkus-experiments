package main.cdi.injectusingannotations.courierpay.cache;

import java.time.Duration;
import java.util.Map;

public abstract class SimpleRedisCache<R, S> implements Cache<R, S> {
    private static final Duration EXPIRATION = Duration.ofMinutes(10);

    @Override
    public void set(Map<R, S> settings) {
        System.out.println("Expiration is : " + getExpirationDuration());
    }

    @Override
    public Duration getExpirationDuration() {
        return EXPIRATION;
    }
}

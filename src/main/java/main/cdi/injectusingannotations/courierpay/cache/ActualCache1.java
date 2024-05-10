package main.cdi.injectusingannotations.courierpay.cache;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class ActualCache1 extends SimpleRedisCache<String, String> {
    @Override
    public Duration getExpirationDuration() {
        return Duration.ofMinutes(5);
    }
}

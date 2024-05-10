package main.cdi.injectusingannotations.courierpay.cache;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class SampleCache extends SimpleRedisCache<Integer, Integer> {
    @Override
    public Duration getExpirationDuration() {
        return Duration.ofMinutes(15);
    }
}

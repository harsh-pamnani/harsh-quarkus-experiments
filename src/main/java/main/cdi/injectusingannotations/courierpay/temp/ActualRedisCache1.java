package main.cdi.injectusingannotations.courierpay.temp;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class ActualRedisCache1 extends SimpleRedisCache<String, String> {
    @Override
    public Duration getExpirationDuration() {
        return Duration.ofMinutes(5);
    }
}

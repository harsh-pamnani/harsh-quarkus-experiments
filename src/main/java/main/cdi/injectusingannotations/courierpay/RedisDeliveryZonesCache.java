package main.cdi.injectusingannotations.courierpay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RedisDeliveryZonesCache implements Cache {
    @Override
    public void save() {
        System.out.println("Saving from Redis cache");
    }
}

package main.cdi.injectusingannotations.courierpay.cache;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActualCache2 extends SimpleRedisCache<String, Integer> {
}

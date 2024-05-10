package main.cdi.injectusingannotations.courierpay.cache;

import java.util.Map;

public abstract class SimpleRedisCache<R, S> implements Cache<R, S> {

    @Override
    public void set(Map<R, S> settings) {
        System.out.println("Expiration is : " + getExpirationDuration());
    }
}

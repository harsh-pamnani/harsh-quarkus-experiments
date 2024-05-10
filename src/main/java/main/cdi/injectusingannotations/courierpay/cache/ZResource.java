package main.cdi.injectusingannotations.courierpay.cache;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Map;

/**
 * Since ActualCache1 has duration override, it would take that.
 * ActualCache2 would take the default duration present in SimpleRedisCache.
 */

@Path("/cache-demo")
public class ZResource {
    Cache<String, String> cache1;
    Cache<String, Integer> cache2;

    // Based on the data type inside cache, appropriate implementation would be injected.
    public ZResource(Cache<String, String> cache1,
                     Cache<String, Integer> cache2) {
        this.cache1 = cache1;
        this.cache2 = cache2;
    }

    @GET
    public String getCacheResponse() {
        cache1.set(Map.of("key1", "value1"));
        cache2.set(Map.of("key2", 2));
        return "Check console for expiration duration";
    }
}

package main.cdi.injectusingannotations.courierpay.cache;

import static main.cdi.injectusingannotations.courierpay.cache.Cache.EXPIRATION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.time.Duration;
import org.junit.jupiter.api.Test;

/**
 * Cache<Integer, Integer> is implemented only by SampleCache. So, Quarkus will inject SampleCache bean.
 */
@QuarkusTest
class CacheTest {
    @Inject
    Cache<String, Integer> cache1;

    @Inject
    Cache<Integer, Integer> cache2;

    @Test
    void testCacheCustomExpiration() {
        assertThat(cache1.getExpirationDuration(), is(EXPIRATION));
        assertThat(cache2.getExpirationDuration(), is(Duration.ofMinutes(15)));
    }
}

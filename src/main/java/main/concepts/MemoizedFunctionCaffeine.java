package main.concepts;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.jbosslog.JBossLog;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@JBossLog
public class MemoizedFunctionCaffeine {

    // Create a cache with a maximum size of 100 and an expiration time of 10 seconds
    private static final Cache<Integer, Integer> cache = Caffeine.newBuilder()
                                                           .maximumSize(100)
                                                           .expireAfterWrite(10, TimeUnit.SECONDS)
                                                           .build();

    public static void main(String[] args) throws InterruptedException {
        log.info("First call will take more time to compute the value");
        log.info("First call: " + compute(4)); // Computes and caches

        log.info("Second and third calls should be instantaneous");
        log.info("Second call: " + compute(4)); // Uses cached value
        log.info("Third call: " + compute(4)); // Uses cached value

        Thread.sleep(Duration.ofSeconds(11));
        // Since cache expires after 10 seconds (configured), fourth call will again compute the value
        log.info("Fourth call: " + compute(4)); // Computes and caches

        // Call with new input value will take time
        log.info("Answer with input 7: " + compute(7)); // Computes and caches
    }

    private static Integer compute(Integer input) {
        return cache.get(input, key -> {
            log.info("Some log running computation");
            try {
                Thread.sleep(Duration.ofSeconds(5));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return input + 2;
        });
    }
}
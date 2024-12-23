package main.concepts;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class MemoizedSupplier {
    public static void main(String[] args) {
        Supplier<Integer> expensiveSupplier = () -> {
            log.info("Some log running computation");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 42;
        };

        Supplier<Integer> memoizedSupplier = Suppliers.memoize(expensiveSupplier);

        log.info("First call will take more time to compute the value");
        log.info("First call: " + memoizedSupplier.get()); // Computes and caches

        log.info("Second and third calls should be instantaneous");
        log.info("Second call: " + memoizedSupplier.get()); // Uses cached value
        log.info("Third call: " + memoizedSupplier.get()); // Uses cached value
    }
}

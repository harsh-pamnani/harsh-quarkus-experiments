package main.concepts.mutiny;

import io.smallrye.mutiny.Uni;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class UniCombineAllWithFailure {
    public static void main(String[] args) {
        Uni<String> uni1 = Uni.createFrom().item(() -> {
            // Simulate a delay
            try {
                Thread.sleep(1000);
                throw new RuntimeException("Task 1 failed");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Uni<String> uni2 = Uni.createFrom().item(() -> {
            try {
                Thread.sleep(5000);
                log.info("Second Uni");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Task 2";
        });

        // discardItems() is used to discard all emitted items and allow the stream to complete without processing any
        // of the items.
        Uni<Void> combinedUni = Uni.combine().all().unis(uni1, uni2).discardItems();

        // If one of the Uni instances fails, combinedUni will fail immediately with the same failure.
        log.info("Uni execution started");
        combinedUni.subscribe().with(log::info);
        log.info("Uni execution ended");
    }
}

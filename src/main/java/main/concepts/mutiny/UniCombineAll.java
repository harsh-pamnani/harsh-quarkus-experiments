package main.concepts.mutiny;

import io.smallrye.mutiny.Uni;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class UniCombineAll {
    public static void main(String[] args) {
        Uni<String> uni1 = Uni.createFrom().item(() -> {
            // Simulate a delay
            try {
                Thread.sleep(1000);
                log.info("First Uni");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Task 1";
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

        Uni<String> combinedUni = Uni.combine()
                                     .all()
                                     .unis(uni1, uni2)
                                     .asTuple()
                                     .map(tuple -> tuple.getItem1() + " " + tuple.getItem2());

        // combinedUni result executed only after both Uni completed
        log.info("Uni execution started");
        combinedUni.subscribe().with(log::info);
        log.info("Uni execution ended");
    }
}

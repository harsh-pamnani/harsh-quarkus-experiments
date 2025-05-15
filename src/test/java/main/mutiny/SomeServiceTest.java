package main.mutiny;

import static main.mutiny.Status.FORWARDED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.operators.uni.builders.UniCreateFromItemSupplier;
import jakarta.inject.Inject;
import java.util.UUID;
import lombok.extern.jbosslog.JBossLog;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@QuarkusTest
@JBossLog
class SomeServiceTest {
    @InjectMock
    SomeRepo someRepo;

    @Inject
    SomeService someService;

    // Here `someRepo.update()` is called, but its internal Uni is not executed.
    // So, `HPXYZ: Update uni executed` will NOT be logged
    // Test case passed because we have NOT added verification that the Uni is subscribed at least once.
    @Test
    void ackMobileEvent_take1() {
        String courierId = UUID.randomUUID().toString();

        doReturn(Uni.createFrom().item(FORWARDED).invoke(() -> log.info("HPXYZ: Find method called")))
                .when(someRepo)
                .find(any());

        doReturn(Uni.createFrom().item("Mobile event from DB").invoke(() -> log.info("HPXYZ: Update uni executed")))
                .when(someRepo)
                .update();

        someService.ackMobileEvent(courierId).await().indefinitely();

        verify(someRepo).update();
    }

    // Here `someRepo.update()` is called, but its internal Uni is not executed.
    // So, `HPXYZ: Update uni executed` will NOT be logged
    // Test case fails because we have verification that the Uni is subscribed at least once. Which is not happening.
    // So, it fails.
    @Disabled
    @Test
    void ackMobileEvent_take2() {
        String courierId = UUID.randomUUID().toString();

        doReturn(Uni.createFrom().item(FORWARDED).invoke(() -> log.info("HPXYZ: Find method called")))
                .when(someRepo)
                .find(any());

        UniCreateFromItemSupplier<String> updatedUni =
                spy(new UniCreateFromItemSupplier<>(() -> "Mobile event from DB"));

        doReturn(updatedUni.invoke(() -> log.info("HPXYZ: Update uni executed")))
                .when(someRepo)
                .update();

        someService.ackMobileEvent(courierId).await().indefinitely();

        verify(updatedUni, atLeastOnce()).subscribe(any());
        verify(someRepo).update();
    }

    // Here `someRepo.update()` is called, and its internal Uni is also executed because `fixedAckMobileEvent` uses
    // `transformToUni`.
    // So, `HPXYZ: Update uni executed` is logged
    @Test
    void fixedAckMobileEvent_take1() {
        String courierId = UUID.randomUUID().toString();

        doReturn(Uni.createFrom().item(FORWARDED).invoke(() -> log.info("HPXYZ: Find method called")))
                .when(someRepo)
                .find(any());

        doReturn(Uni.createFrom().item("Mobile event from DB").invoke(() -> log.info("HPXYZ: Update uni executed")))
                .when(someRepo)
                .update();

        someService.fixedAckMobileEvent(courierId).await().indefinitely();

        verify(someRepo).update();
    }

    // Here `someRepo.update()` is called, and its internal Uni is also executed because `fixedAckMobileEvent` uses
    // `transformToUni`.
    // So, `HPXYZ: Update uni executed` will NOT be logged
    @Test
    void fixedAckMobileEvent_take2() {
        String courierId = UUID.randomUUID().toString();

        doReturn(Uni.createFrom().item(FORWARDED).invoke(() -> log.info("HPXYZ: Find method called")))
                .when(someRepo)
                .find(any());

        UniCreateFromItemSupplier<String> updatedUni =
                spy(new UniCreateFromItemSupplier<>(() -> "Mobile event from DB"));

        doReturn(updatedUni.invoke(() -> log.info("HPXYZ: Update uni executed")))
                .when(someRepo)
                .update();

        someService.fixedAckMobileEvent(courierId).await().indefinitely();

        verify(updatedUni, atLeastOnce()).subscribe(any());
        verify(someRepo).update();
    }
}

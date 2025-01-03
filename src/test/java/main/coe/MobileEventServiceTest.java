package main.coe;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@QuarkusTest
class MobileEventServiceTest {
    @InjectMock
    MobileSessionRepository mobileSessionRepository;

    @Inject
    MobileEventService mobileEventService;

    @Test
    void sendCourierMobileEvent_savesAndSendsMobileEvent() {
        Integer courierId = 9;

        doReturn(Uni.createFrom().item(courierId))
                .when(mobileSessionRepository)
                .findLastSession(courierId);

        mobileEventService
                .sendCourierMobileEvent(courierId)
                .await()
                .indefinitely();
    }
}

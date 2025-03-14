package main.mutiny;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.jbosslog.JBossLog;

import java.util.Optional;

@ApplicationScoped
@JBossLog
public class SomeApi {
    private final SomeService someService;

    @Inject
    public SomeApi(SomeService someService) {
        this.someService = someService;
    }

    public Uni<Void> ackCourierMobileEvent(String courierId) {
        log.info("HPXYZ : SomeApi ackCourierMobileEvent called");
        return someService.ackMobileEvent(courierId);
    }
}

package main.mutiny;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@JBossLog
public class SomeService {

    private final SomeRepo someRepo;

    @Inject
    public SomeService(SomeRepo someRepo) {
        this.someRepo = someRepo;
    }

    public Uni<Void> ackMobileEvent(String courierId) {
        log.info("HPXYZ : SomeRepo ackMobileEvent called");
        return someRepo
                .find(courierId)
                .onItem()
                .transform(Unchecked.function(status -> switch (status) {
                    case FORWARDED -> someRepo.update();
                    case ACKNOWLEDGED -> throw new RuntimeException("Already acknowledged");
                    case OVERRIDDEN -> throw new RuntimeException("Already overridden");
                }))
                .replaceWithVoid();
    }

    public Uni<Void> fixedAckMobileEvent(String courierId) {
        log.info("HPXYZ : SomeRepo fixedAckMobileEvent called");
        return someRepo
                .find(courierId)
                .onItem()
                .transformToUni(Unchecked.function(status -> switch (status) {
                    case FORWARDED -> someRepo.update();
                    case ACKNOWLEDGED -> throw new RuntimeException("Already acknowledged");
                    case OVERRIDDEN -> throw new RuntimeException("Already overridden");
                }))
                .replaceWithVoid();
    }
}

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

    // someRepo.update() returns Uni<String>, and we are using `transform`, which results in Uni<Uni<String>>.
    // As a consequence, there is no subscriber for the inner Uni.
    // It means the update method gets called, but the inner Uni never executes, preventing the `Item retrieved from DB`
    // log from appearing.
    public Uni<Void> ackMobileEvent(String courierId) {
        log.info("HPXYZ : SomeRepo ackMobileEvent called");
        return someRepo.find(courierId)
                .onItem()
                .transform(Unchecked.function(status -> switch (status) {
                    case FORWARDED -> someRepo.update();
                    case ACKNOWLEDGED -> throw new RuntimeException("Already acknowledged");
                    case OVERRIDDEN -> throw new RuntimeException("Already overridden");
                }))
                .replaceWithVoid();
    }

    // someRepo.update() returns Uni<String> and we are using TransformToUni. So, the effective result would be
    // Uni<String>
    // It means the update method gets called, and the inner Uni executes as well, generating the `Item retrieved from
    // DB` log.
    public Uni<Void> fixedAckMobileEvent(String courierId) {
        log.info("HPXYZ : SomeRepo fixedAckMobileEvent called");
        return someRepo.find(courierId)
                .onItem()
                .transformToUni(Unchecked.function(status -> switch (status) {
                    case FORWARDED -> someRepo.update();
                    case ACKNOWLEDGED -> throw new RuntimeException("Already acknowledged");
                    case OVERRIDDEN -> throw new RuntimeException("Already overridden");
                }))
                .replaceWithVoid();
    }
}

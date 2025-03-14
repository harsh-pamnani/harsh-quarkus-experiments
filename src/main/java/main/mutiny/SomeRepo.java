package main.mutiny;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;

import java.util.Optional;

@ApplicationScoped
@JBossLog
public class SomeRepo {
    public Uni<Status> find(String courierId) {
        log.info("HPXYZ : SomeRepo find called with courierId = " + courierId);
        return Uni.createFrom().item(() -> {
            if ("1".equals(courierId)) {
                return Status.FORWARDED;
            } else if ("2".equals(courierId)) {
                return Status.ACKNOWLEDGED;
            } else {
                return Status.OVERRIDDEN;
            }
        });
    }

    public Uni<String> update() {
        log.info("HPXYZ : SomeRepo updated called");
        return Uni.createFrom().item("ignored");
    }
}

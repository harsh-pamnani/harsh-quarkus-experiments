package main.coe;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MobileSessionRepository {
    public Uni<Integer> findLastSession(Integer courierId) {
        return Uni.createFrom().item(courierId);
    }
}

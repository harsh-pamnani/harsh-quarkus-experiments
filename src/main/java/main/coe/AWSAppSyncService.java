package main.coe;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;

import java.io.IOException;

@ApplicationScoped
@JBossLog
public class AWSAppSyncService {
    public Uni<Void> sendCourierMobileEvent(Integer input) {
        return Uni.createFrom()
                  .item(input)
                  .onItem()
                  .transformToUni(payload -> {
                      try {
                          someLongProcess(payload);
                          return Uni.createFrom().voidItem();
                      } catch (IOException e) {
                          log.error("Error publishing courier mobile event", e);
                          return Uni.createFrom().failure(e);
                      }
                  })
                  .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
                  .replaceWithVoid();
    }

    private void someLongProcess(Integer input) throws IOException {
        if (input == 10) {
            throw new IOException("Unexpected error occurred");
        }
        if (input == 20) {
            throw new RuntimeException("Unexpected runtime error occurred");
        }
    }
}

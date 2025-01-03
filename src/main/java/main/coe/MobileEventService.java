package main.coe;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
@ApplicationScoped
public class MobileEventService {
    private final AWSAppSyncService awsAppSyncService;
    private final MobileSessionRepository mobileSessionRepository;

    public MobileEventService(MobileSessionRepository mobileSessionRepository, AWSAppSyncService awsAppSyncService) {
        this.awsAppSyncService = awsAppSyncService;
        this.mobileSessionRepository = mobileSessionRepository;
    }

    public Uni<Void> sendCourierMobileEvent(Integer courierId) {
        return mobileSessionRepository.findLastSession(courierId).onItem().transformToUni(foundSession -> {
            Integer input = foundSession;

            return Uni.combine()
                      .all()
                      .unis(awsAppSyncService.sendCourierMobileEvent(input))
                      .discardItems();
        });
    }
}

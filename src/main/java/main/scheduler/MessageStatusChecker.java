package main.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

@ApplicationScoped
@JBossLog
public class MessageStatusChecker {

    public static final int DUMMY_EVENTS_COUNT = 10;
    public static final int MAXIMUM_RETRIES = 3;

    private final ConcurrentMap<EventData, Integer> retryDataMap = new ConcurrentHashMap<>();

    public MessageStatusChecker() {
        initializeMapWithDummyValues();
    }

    public void scheduleMessageForRetry(String sessionId, Integer offset) {
        retryDataMap.put(new EventData(sessionId, offset), 0); // 0 represents the count for number of retries. Starting with 0
    }

    @Scheduled(every = "10s")
    void checkMessageStatus() {
        log.info("Data BEFORE scheduled job block: " + retryDataMap);

        retryDataMap.forEach((data, count) -> {
            if (count >= MAXIMUM_RETRIES) {
                log.info("MAXIMUM retries reached. Cancelling the message. sessionId: " + data.sessionId() + ", offset: " + data.offset());
                retryDataMap.remove(data);
                return;
            }

            if (isAcknowledged(data.sessionId(), data.offset())) {
                log.info("Message IS acknowledged, removing it from retry data. sessionId: " + data.sessionId() + ", offset: " + data.offset());
                retryDataMap.remove(data);
            } else {
                log.info("Message IS NOT acknowledged, increasing retry count. sessionId: " + data.sessionId() + ", offset: " + data.offset());
                retryDataMap.put(data, count + 1);
            }
        });

        log.info("Data AFTER scheduled job block: " + retryDataMap);

    }

    private boolean isAcknowledged(String sessionId, Integer offset) {
        // Call the dynamo repo to check if the message has been acknowledged or not
        return new Random().nextBoolean();
    }

    private void initializeMapWithDummyValues() {
        IntStream.range(0, DUMMY_EVENTS_COUNT).forEach(i -> {
            String sessionId = "session-" + i;
            Integer offset = new Random().nextInt(10);
            EventData eventData = new EventData(sessionId, offset);
            retryDataMap.put(eventData, 0);
        });
    }
}

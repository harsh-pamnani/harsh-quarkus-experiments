package main.scheduler;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/send-event")
public class MobileEventService {
    MessageStatusChecker messageStatusChecker;

    @Inject
    public MobileEventService(MessageStatusChecker messageStatusChecker) {
        this.messageStatusChecker = messageStatusChecker;
    }

    @POST
    public void sendCourierMobileEvent(EventData eventData) {
        /*
        Do other stuff for storing the event to DynamoDB and sending it to AppSync mutation
        */

        // Now, add the message in the list to be checked for retries
        messageStatusChecker.scheduleMessageForRetry(eventData.sessionId(), eventData.offset());
    }
}

package main.temporal;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class PizzaActivitiesImpl implements PizzaActivities {
    @Override
    public String takeOrder(String orderId) {
        log.info("Order taken.");
        return "Order taken. Order ID: " + orderId;
    }

    /*
    Scenario-1:
    Set the delay to 21000. This will result in a StartToCloseTimeout failure for the first two attempts,
    and a ScheduleToCloseTimeout failure on the third attempt, as the total time will exceed 50 seconds by then.
    The fourth attempt will not occur, even though the maximum attempts are set to 4.

    Scenario-2:
    Set the delay to 10000 milliseconds. Hit the endpoint to start the workflow, then immediately stop the application.
    Monitor workflow on the Temporal. First attempt will fail with a StartToCloseTimeout, as the response is not received within 20 sec.
    Once the second attempt begins, restart the app. Temporal will automatically continue and complete the request from the activity
        it left off (chargeCustomer activity), without requiring manual intervention, because it remembers the state of the workflows.
    In contrast, if this were a standard circuit breaker fault tolerance, the application would not remember where it stopped, requiring
        us to manually re-execute the entire request.
    */

    @Override
    public void chargeCustomer(String customerId) {
        log.info("Starting to charge.");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("Charged: " + customerId);
    }

    @Override
    public void deliverPizza() {
        log.info("Pizza delivered to customer.");
    }
}

package main.temporal;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class PizzaActivitiesImpl implements PizzaActivities {
    @Override
    public void takeOrder() {
        log.info("Order taken.");
    }

    @Override
    public void chargeCustomer(String customerId) {
        try {
            // Set the delay to 11000. It would result in StartToCloseTimeout failure for first 2 attempts,
            // and ScheduleToCloseTimeout failure for the third attempt as it would have exceeded 25 seconds by then.
            // It won't go to fourth attempt even though maximum attempts are set to 4.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("Charged: " + customerId);
    }

    @Override
    public void deliverPizza() {
        log.info("Pizza delivered to.");
    }
}
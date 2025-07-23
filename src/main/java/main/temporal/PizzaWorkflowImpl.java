package main.temporal;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;

public class PizzaWorkflowImpl implements PizzaWorkflow {
    // StartToCloseTimeout    - Max execution time for a single attempt of the activity
    // ScheduleToCloseTimeout - Max total time including retries before giving up entirely
    // RetryOptions           - Configs on how it will be retried. Here we are setting max attempts to 4.

    private final PizzaActivities activities = Workflow.newActivityStub(
            PizzaActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(20))
                    .setScheduleToCloseTimeout(Duration.ofSeconds(50))
                    .setRetryOptions(
                            RetryOptions.newBuilder().setMaximumAttempts(4).build())
                    .build());

    @Override
    public String startOrder(String customerId, String orderId) {
        activities.takeOrder(orderId);
        activities.chargeCustomer(customerId);
        activities.deliverPizza();
        return "Order completed for customer: " + customerId + ", Order ID: " + orderId;
    }
}

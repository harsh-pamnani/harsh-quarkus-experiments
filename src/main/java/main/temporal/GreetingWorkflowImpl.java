package main.temporal;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class GreetingWorkflowImpl implements GreetingWorkflow {
    // StartToCloseTimeout    - Max execution time for a single attempt of the activity
    // ScheduleToCloseTimeout - Max total time including retries before giving up entirely
    // RetryOptions           - Configs on how it will be retried. Here we are setting max attempts to 4.

    private final GreetingActivities activities = Workflow.newActivityStub(
            GreetingActivities.class,
            ActivityOptions.newBuilder()
                           .setStartToCloseTimeout(Duration.ofSeconds(10))
                           .setScheduleToCloseTimeout(Duration.ofSeconds(25))
                           .setRetryOptions(RetryOptions.newBuilder()
                                                        .setMaximumAttempts(4)
                                                        .build())
                           .build()
    );

    @Override
    public String greet(String name) {
        String hello = activities.sayHello(name);
        String goodbye = activities.sayGoodbye(name);
        return hello + " and " + goodbye;
    }
}
package main.temporal;

public class GreetingActivitiesImpl implements GreetingActivities {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public String sayGoodbye(String name) {
        try {
            // Set the delay to 11000. It would result in StartToCloseTimeout failure for first 2 attempts,
            // and ScheduleToCloseTimeout failure for the third attempt as it would have exceeded 25 seconds by then.
            // It won't go to fourth attempt even though maximum attempts are set to 4.
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Interrupted while saying goodbye to " + name;
        }
        return "Goodbye " + name;
    }
}
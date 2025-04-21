package main.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface GreetingActivities {
    @ActivityMethod
    String sayHello(String name);

    @ActivityMethod
    String sayGoodbye(String name);
}

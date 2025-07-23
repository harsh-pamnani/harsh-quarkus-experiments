package main.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import java.util.Optional;

@ActivityInterface
public interface PizzaActivities {
    @ActivityMethod
    String takeOrder(String orderId);

    @ActivityMethod
    String chargeCustomer(String customerId);

    @ActivityMethod
    String deliverPizza(Optional<Integer> tip);
}

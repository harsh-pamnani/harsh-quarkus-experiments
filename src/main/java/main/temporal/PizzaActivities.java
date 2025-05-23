package main.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PizzaActivities {
    @ActivityMethod
    void takeOrder();

    @ActivityMethod
    void chargeCustomer(String customerId);

    @ActivityMethod
    void deliverPizza();
}

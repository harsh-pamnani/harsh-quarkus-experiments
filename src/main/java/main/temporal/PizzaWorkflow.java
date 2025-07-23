package main.temporal;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface PizzaWorkflow {
    @WorkflowMethod
    void startOrder(String customerId, String orderId);
}

package main.temporal;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface PizzaWorkflow {
    String TIP_UPDATED_SIGNAL_NAME = "TipUpdatedSignal";
    Integer TIP_UPDATE_WAITING_TIME = 2;

    @WorkflowMethod
    String startOrder(String customerId, String orderId);

    @SignalMethod(name = TIP_UPDATED_SIGNAL_NAME)
    void signalTipUpdated(Integer newTip);
}

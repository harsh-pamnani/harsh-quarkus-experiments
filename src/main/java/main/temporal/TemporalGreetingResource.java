package main.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/temporal")
public class TemporalGreetingResource {
    private WorkflowClient workflowClient;

    @Inject
    public TemporalGreetingResource(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    @GET
    @Path("/order-pizza")
    public String orderPizza(@QueryParam("customerId") String customerId, @QueryParam("orderId") String orderId) {
        PizzaWorkflow workflow = workflowClient.newWorkflowStub(
                PizzaWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue("PIZZA_QUEUE")
                        .setWorkflowId(orderId)
                        .build());

        workflow.startOrder(customerId, orderId);
        return "Pizza ";
    }

    @GET
    @Path("/signal-tip-change")
    public String signalTipChange(@QueryParam("orderId") String orderId, @QueryParam("newTip") Integer newTip) {
        // We need to pass workflowId to the workflow stub to signal it. In our case, orderId is used as the workflowId.
        var workflow = workflowClient.newUntypedWorkflowStub(orderId);

        try {
            workflow.signal(PizzaWorkflow.TIP_UPDATED_SIGNAL_NAME, newTip);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to signal tip";
        }

        return "Tip received for order: " + orderId;
    }
}

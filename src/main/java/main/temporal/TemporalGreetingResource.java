package main.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/temporal-order-pizza")
public class TemporalGreetingResource {
    @GET
    public String orderPizza(@QueryParam("customerId") String customerId, @QueryParam("orderId") String orderId) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        PizzaWorkflow workflow = client.newWorkflowStub(
                PizzaWorkflow.class,
                WorkflowOptions.newBuilder()
                               .setTaskQueue("PIZZA_QUEUE")
                               .setWorkflowId(orderId)
                               .build()
        );

        workflow.startOrder(customerId);
        return "Pizza ";
    }
}

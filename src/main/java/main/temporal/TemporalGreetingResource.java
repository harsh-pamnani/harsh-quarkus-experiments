package main.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/temporal-greet")
public class TemporalGreetingResource {
    @GET
    public String greet(@QueryParam("name") String name) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        GreetingWorkflow workflow = client.newWorkflowStub(
                GreetingWorkflow.class,
                WorkflowOptions.newBuilder()
                               .setTaskQueue("greeting-task-queue-name")
                               .build()
        );

        return workflow.greet(name);
    }
}

package main.temporal;

import io.quarkus.runtime.Startup;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Startup
public class TemporalWorkerStarter {

    @PostConstruct
    void init() {
        /*
        By default, the temporal instance connects to "localhost:7233" - which is where our temporal server is running.
        If we want to update the address, we can use setTarget.

        WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder()
                                                                    .setTarget("temporal.yourdomain.com:443")
                                                                    .build());
        */

        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("PIZZA_QUEUE");

        worker.registerWorkflowImplementationTypes(PizzaWorkflowImpl.class);
        worker.registerActivitiesImplementations(new PizzaActivitiesImpl());

        factory.start();
    }
}

package main.temporal;

import io.quarkus.runtime.Startup;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@Startup
@JBossLog
public class TemporalWorkerStarter {

    @PostConstruct
    void init() {
        log.info("Starting TemporalWorkerStarter");
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("PIZZA_QUEUE");

        worker.registerWorkflowImplementationTypes(PizzaWorkflowImpl.class);
        worker.registerActivitiesImplementations(new PizzaActivitiesImpl());

        factory.start();
        log.info("Started TemporalWorkerStarter");
    }
}

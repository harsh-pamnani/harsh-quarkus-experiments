package main.cdi.lock.exercise;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import main.cdi.lock.ExampleBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Path("/exercise")
public class CoffeeResource {
    @Inject
    Exercise exerciseBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String makeCoffeeWithoutLock() throws InterruptedException {
        return "Doing exercise. Counter is " + exerciseBean.getCounter();
    }
}

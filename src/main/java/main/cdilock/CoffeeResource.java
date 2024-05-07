package main.cdilock;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Build and run the native image
 * Hit `/coffee/without-lock` multiple times and see that you will get different increased count every time
 * Hit `/coffee/with-lock` multiple times and see it will return consistent count every time (will increase by 5000 consistently)
 */
@Path("/coffee")
public class CoffeeResource {
    @Inject
    ExampleBean exampleBean;

    @GET
    @Path("/without-lock")
    @Produces(MediaType.TEXT_PLAIN)
    public String makeCoffeeWithoutLock() throws InterruptedException {
        makeCoffee(false);
        return "Amazing coffee (made without lock). Counter is " + exampleBean.getCounter();
    }

    @GET
    @Path("/with-lock")
    @Produces(MediaType.TEXT_PLAIN)
    public String makeCoffeeWithLock() throws InterruptedException {
        makeCoffee(true);
        return "Amazing coffee (made with lock). Counter is " + exampleBean.getCounter();
    }

    private void makeCoffee(boolean isWithLock) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                if (isWithLock) {
                    exampleBean.incrementCounterWithLock();
                } else {
                    exampleBean.incrementCounterWithoutlock();
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            executorService.submit(task);
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}

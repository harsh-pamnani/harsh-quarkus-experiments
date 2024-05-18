package main.cdi.lock;

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
 * Hit `/drink/without-lock` multiple times and see that you will get different increased count every time
 * Hit `/drink/with-lock` multiple times and see it will return consistent count every time (will increase by 5000 consistently)
 */
@Path("/drink")
public class DrinkResource {
    @Inject
    ExampleBean exampleBean;

    @GET
    @Path("/without-lock")
    @Produces(MediaType.TEXT_PLAIN)
    public String makeDrinkWithoutLock() throws InterruptedException {
        makeDrink(false);
        return "Amazing drink (made without lock). Counter is " + exampleBean.getCounter();
    }

    @GET
    @Path("/with-lock")
    @Produces(MediaType.TEXT_PLAIN)
    public String makeDrinkWithLock() throws InterruptedException {
        makeDrink(true);
        return "Amazing drink (made with lock). Counter is " + exampleBean.getCounter();
    }

    private void makeDrink(boolean isWithLock) throws InterruptedException {
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

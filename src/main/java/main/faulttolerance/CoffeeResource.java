package main.faulttolerance;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;

// Reference - https://quarkus.io/guides/smallrye-fault-tolerance
/**
 * GET /coffee
 * The CoffeeResource#coffees() method is still in fact failing in about 50 % of time, but every time it happens,
 *      the app will automatically retry the call. Max retries will be 4 times. If it still fails after 4 retires, it will throw error.
 * maxRetries is set to 4, but it is also set by application.properties as 10. So, it will take priority at run time.
 *
 * GET /coffee/{id}/recommendations
 * Note that the timeout was configured to 250 ms, and a random artificial delay between 0 and 500 ms was introduced
 * In your browser, go to http://localhost:8080/coffee/2/recommendations and hit refresh a couple of times.
 * You should see some requests time out with TimeoutException. Requests that do not time out should show two recommended coffee.
 * If @Fallback is introduced, it will fall back to given method in case of timeouts
 *
 * GET /{id}/availability
 * This endpoint shows the usage of circuit breaker.
 * Hit the endpoint multiple times and see how the availability changes.
 * We added a @CircuitBreaker annotation to getAvailability method with requestVolumeThreshold = 4.
 * CircuitBreaker.failureRatio is by default 0.5, and CircuitBreaker.delay is by default 5 seconds.
 * That means that a circuit breaker will open when 2 of the last 4 invocations failed, and it will stay open for 5 seconds.
 */
@Path("/coffee")
public class CoffeeResource {

    private static final Logger LOGGER = Logger.getLogger(CoffeeResource.class);

    @Inject
    CoffeeRepositoryService coffeeRepository;

    private AtomicLong counter = new AtomicLong(0);

    //
    @GET
    @Retry(maxRetries = 4)
    public List<Coffee> coffees() {
        final Long invocationNumber = counter.getAndIncrement();

        maybeFail(String.format("CoffeeResource#coffees() invocation #%d failed", invocationNumber));

        LOGGER.infof("CoffeeResource#coffees() invocation #%d returning successfully", invocationNumber);
        return coffeeRepository.getAllCoffees();
    }

    @GET
    @Path("/{id}/recommendations")
//    @Fallback(fallbackMethod = "fallbackRecommendations")
    @Timeout(250)
    public List<Coffee> recommendations(@PathParam("id") int id) {
        long started = System.currentTimeMillis();
        final long invocationNumber = counter.getAndIncrement();

        try {
            randomDelay();
            LOGGER.infof("CoffeeResource#recommendations() invocation #%d returning successfully", invocationNumber);
            return coffeeRepository.getRecommendations(id);
        } catch (InterruptedException e) {
            LOGGER.errorf("CoffeeResource#recommendations() invocation #%d timed out after %d ms",
                          invocationNumber, System.currentTimeMillis() - started);
            return null;
        }
    }

    private void maybeFail(String failureLogMessage) {
        if (new Random().nextInt(1, 15) > 1) {
            LOGGER.error(failureLogMessage);
            throw new RuntimeException("Resource failure.");
        }
    }

    private void randomDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    }

    public List<Coffee> fallbackRecommendations(int id) {
        LOGGER.info("Falling back to RecommendationResource#fallbackRecommendations()");
        // safe bet, return something that everybody likes
        return Collections.singletonList(new Coffee(11, "fallback coffee", "XYZ", 10));
    }




    @Path("/{id}/availability")
    @GET
    public Response availability(int id) {
        final Long invocationNumber = counter.getAndIncrement();

        Coffee coffee = coffeeRepository.getCoffeeById(id);
        // check that coffee with given id exists, return 404 if not
        if (coffee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            Integer availability = coffeeRepository.getAvailability(coffee);
            LOGGER.infof("CoffeeResource#availability() invocation #%d returning successfully", invocationNumber);
            return Response.ok(availability).build();
        } catch (RuntimeException e) {
            String message = e.getClass().getSimpleName() + ": " + e.getMessage();
            LOGGER.errorf("CoffeeResource#availability() invocation #%d failed: %s", invocationNumber, message);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(message)
                           .type(MediaType.TEXT_PLAIN_TYPE)
                           .build();
        }
    }
}
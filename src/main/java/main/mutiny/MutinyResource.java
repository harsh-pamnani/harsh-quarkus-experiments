package main.mutiny;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.Duration;

/**
 * 1. Context:
 *      - `/get1` endpoint return the results immediately.
 *      - `/get2` returns the result after 10 seconds
 *      - `/get3` returns the result after 5 seconds, but it returns with `Uni`.
 *      - `quarkus.thread-pool.max-threads` is set to 1. That means I/O thread pool will have maximum 1 thread.
 *          This property is particularly important when you're dealing with applications that perform a lot of I/O-bound operations,
 *          such as handling HTTP requests or interacting with databases.
 * 2. Scenario-1:
 *      If you call the `/get2` endpoint and then immediately call `/get1`, you'll notice that the `/get1` results won't be available
 *      until `/get2` is fully processed. This happens because `/get2` is blocking the current IO thread entirely.
 *      Also, since the maximum thread size is set to 1, it can't have another thread.
 * 3. Scenario-2:
 *      If you hit the `/get3` endpoint and then immediately call `/get1`, you'll see that the `/get1` results show up right away, even though
 *      `/get3` is still being processed. This happens because we’re using `Uni<String>` for `/get3`, so the original IO thread gets freed
 *      up while waiting for the results, making things faster.
 * 4. Scenario-3:
 *      Set `quarkus.thread-pool.max-threads` to `2` in `application.properties` and repeat Scenario-1. Now you will observe that `/get1`
 *      is processed immediately because now we have 2 threads.
 */
@Path("/mutiny")
public class MutinyResource {
    @GET
    @Path("/get1")
    public String get1() {
        return "Normal Get";
    }

    @GET
    @Path("/get2")
    public String get2() {
        try {
            Thread.sleep(10000); // Some long-running process
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Normal Get with 10 second delay";
    }

    @GET
    @Path("/get3")
    public Uni<String> get3() {
        return Uni.createFrom().item("Uni Get").onItem().delayIt().by(Duration.ofSeconds(5));
    }
}

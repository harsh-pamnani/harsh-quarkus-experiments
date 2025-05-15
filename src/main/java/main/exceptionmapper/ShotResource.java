package main.exceptionmapper;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

/**
 * Hit `POST http://localhost:9999/shot` endpoint with body as `{"rowNumber": -1}`.
 * See that it generates exception with `406` error - utilizing the customer exception mapper.
 */
@Path("/shot")
public class ShotResource {
    @GET
    public String shootCannon() {
        return "Hello from GET shot";
    }

    @POST
    public String shootCannon(Shot shot) {
        if (shot.rowNumber() <= 0) {
            throw new InvalidRowNumberException("Row number can't be negative");
        }
        return "Post shot completed";
    }
}

package main.exceptionmapper;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

/**
 * IMPORTANT - For now, it doesn't work with native image build due to some GraalVM issues.
 * So, enable "quarkus-rest-jackson" and run Quarkus application in IntelliJ IDEA to see the output of exception mappers.
 * Hit `POST http://localhost:8080/shot` endpoint with body as `{"rowNumber": -1}`.
 * See that it generates exception with `406` error - utilizing the customer exception mapper.
 */

@Path("/shot")
public class ShotResource {
    @GET
    public String shootCannon(){
        return "Hello from GET shot";
    }

    @POST
    public String shootCannon(Shot shot){
        if(shot.rowNumber() <= 0){
            throw new InvalidRowNumberException("Row number can't be negative");
        }
        return "Post shot completed";
    }
}

package main.multiplepathwithsameresource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class MultiplePathResource {

    @GET
    @Path("/{prefix:(customers/profiles)?/?}v1/skip-pay")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "This works for both /v1/skip-pay and /customers/profiles/v1/skip-pay";
    }
}

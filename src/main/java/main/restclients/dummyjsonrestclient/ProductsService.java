package main.restclients.dummyjsonrestclient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/products")
@RegisterRestClient(configKey = "dummy-json-client")
public interface ProductsService {

    @GET
    @Path("{id}")
    Product getById(String id);
}

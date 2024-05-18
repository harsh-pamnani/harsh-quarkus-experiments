package main.restclients.dummyjsonrestclient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.Set;

@Path("/products")
@RegisterRestClient(configKey = "dummy-json-client")
public interface ProductsService {

    @GET
    @Path("{id}")
    Product getById(String id);
}
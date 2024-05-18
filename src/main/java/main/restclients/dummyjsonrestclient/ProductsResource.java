package main.restclients.dummyjsonrestclient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestPath;

import java.util.Set;

/**
 * View docs at https://dummyjson.com/
 *
 * https://dummyjson.com/products/1 gives product with id=1.
 */
@Path("/products")
public class ProductsResource {

    @RestClient
    ProductsService productsService;

    @GET
    @Path("/{id}")
    public Product id(@RestPath String id) {
        return productsService.getById(id);
    }
}
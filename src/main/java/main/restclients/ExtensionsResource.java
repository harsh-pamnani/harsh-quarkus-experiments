package main.restclients;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestPath;

import java.util.Set;

/**
 * http://localhost:9999/extension/id/io.quarkus:quarkus-rest-client
 *
 * Hit https://stage.code.quarkus.io/api/extensions to see the whole response
 */
@Path("/extension")
public class ExtensionsResource {

    @RestClient
    ExtensionsService extensionsService;

    @GET
    @Path("/id/{id}")
    public Set<Extension> id(@RestPath String id) {
        return extensionsService.getById(id);
    }
}
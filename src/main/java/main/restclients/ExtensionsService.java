package main.restclients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.Set;

@Path("/extensions")
@RegisterRestClient(configKey = "quarkus-extension-random-key")
public interface ExtensionsService {

    @GET
    Set<Extension> getById(@QueryParam("id") String id);

    @GET
    @Path("/stream/{stream}")
    Set<Extension> getByStream(@PathParam("stream") String stream, @RestQuery String id);
}
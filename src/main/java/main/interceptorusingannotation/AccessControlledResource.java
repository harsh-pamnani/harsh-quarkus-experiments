package main.interceptorusingannotation;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/access-controlled")
public class AccessControlledResource {

    private final AccessControlApi accessControlApi;

    @Inject
    public AccessControlledResource(AccessControlApi accessControlApi) {
        this.accessControlApi = accessControlApi;
    }

    // This public resource is exposed externally, so it must be secured using the custom @PublicResource annotation.
    // The interceptor processes requests, logs any exceptions, and returns a generic "Unexpected error occurred" if an error occurs.
    @GET
    @Path("/public")
    @PublicResource
    public String publicResource() {
        accessControlApi.someMethodThrowingSecretDbError();
        return "public";
    }

    // This private resource is used exclusively by internal services, so the @PublicResource annotation is not required.
    // If an exception occurs, the actual error will be returned.
    @GET
    @Path("/private")
    public String privateResource() {
        accessControlApi.someMethodThrowingSecretDbError();
        return "private";
    }
}

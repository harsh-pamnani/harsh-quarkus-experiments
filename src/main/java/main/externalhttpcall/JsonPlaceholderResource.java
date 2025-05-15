package main.externalhttpcall;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/json-placeholder")
public class JsonPlaceholderResource {
    @Inject
    JsonPlaceholderService jsonPlaceholderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonPlaceholder() throws IOException {
        return jsonPlaceholderService.makeDummyJsonPlaceholderCall();
    }
}

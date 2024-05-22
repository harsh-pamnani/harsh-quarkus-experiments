package main.redis;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.text.ParseException;
import java.util.Optional;

@Path("/author")
public class AuthorResource {

    AuthorApi authorApi;

    public AuthorResource(AuthorApi authorApi) {
        this.authorApi = authorApi;
    }

    @GET
    @Path("/{id}")
    public Optional<Author> getAuthor(String id) {
        return authorApi.getAuthor(id);
    }

    @POST
    public Author createNewAuthor(CreateAuthorDto author) throws ParseException {
        return authorApi.saveAuthorToRedis(author);
    }
}

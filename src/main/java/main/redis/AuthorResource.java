package main.redis;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.Optional;

@Path("/author")
public class AuthorResource {

    AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GET
    @Path("/{id}")
    public Optional<Author> getAuthor(String id) {
        return authorService.getAuthor(id);
    }

    @POST
    public Author createNewAuthor(CreateAuthorDto author) {
        return authorService.saveAuthorToRedis(author);
    }

    @DELETE
    @Path("/{id}")
    public void removeAuthor(String id) {
        authorService.removeAuthor(id);
    }
}

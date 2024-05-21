package main.redis;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/author")
public class AuthorResource {

    AuthorRedisService authorRedisService;

    public AuthorResource(AuthorRedisService authorRedisService) {
        this.authorRedisService = authorRedisService;
    }

    @GET
    public Author getAuthor() {
        return authorRedisService.get("author_111");
    }

    @POST
    public Author createNewAuthor() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = dateFormat.parse("1775-12-16");
        Author author = new Author("111", "Name for Redis", birthdate, "CAN");
        authorRedisService.saveAuthorToRedis(author);
        return author;
    }
}

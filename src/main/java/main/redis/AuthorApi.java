package main.redis;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class AuthorApi {
    AuthorRedisService authorRedisService;

    public AuthorApi(AuthorRedisService authorRedisService) {
        this.authorRedisService = authorRedisService;
    }

    public Optional<Author> getAuthor(String id) {
        return authorRedisService.get(id);
    }

    public Author saveAuthorToRedis(CreateAuthorDto author) {
        Author authorToSave = author.toDomain();
        authorRedisService.save(authorToSave);
        return authorToSave;
    }
}

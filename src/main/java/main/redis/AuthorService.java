package main.redis;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class AuthorService {
    RedisRepo redisRepo;

    public AuthorService(RedisRepo redisRepo) {
        this.redisRepo = redisRepo;
    }

    public Optional<Author> getAuthor(String id) {
        return redisRepo.get(id);
    }

    public Author saveAuthorToRedis(CreateAuthorDto author) {
        Author authorToSave = author.toDomain();
        redisRepo.save(authorToSave);
        return authorToSave;
    }

    public void removeAuthor(String id) {
        redisRepo.del(id);
    }
}

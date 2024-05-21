package main.redis;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class AuthorRedisService {
    private final ReactiveKeyCommands<String> keyCommands;
    private final ValueCommands<String, Author> authorCommands;

    public AuthorRedisService(RedisDataSource ds,
                              ReactiveRedisDataSource reactive) {
        keyCommands = reactive.key();
        authorCommands = ds.value(Author.class);
    }

    public void saveAuthorToRedis(Author author) {
        set(createRedisKey(author.id()), author);
    }

    Author get(String key) {
        Author value = authorCommands.get(key);
        if (value == null) {
            throw new RuntimeException("can't get author");
        }
        return value;
    }

    void set(String key, Author author) {
        authorCommands.set(key, author);
    }

    Uni<Void> del(String key) {
        return keyCommands.del(key)
                          .replaceWithVoid();
    }

    Uni<List<String>> keys() {
        return keyCommands.keys("*");
    }

    private String createRedisKey(String id) {
        return "author_" + id;
    }
}

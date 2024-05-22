package main.redis;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.SetArgs;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RedisRepo {
    private final ValueCommands<String, Author> authorCommands;
    private final ReactiveKeyCommands<String> keyCommands;
    private final SetArgs setArgs;

    private static final Duration EXPIRATION = Duration.ofMinutes(10);
    private static final Logger LOGGER = Logger.getLogger(RedisRepo.class);

    public RedisRepo(RedisDataSource ds,
                     ReactiveRedisDataSource reactive) {
        authorCommands = ds.value(Author.class);
        keyCommands = reactive.key();
        setArgs = new SetArgs().ex(EXPIRATION);
    }

    Optional<Author> get(String key) {
        Author value = authorCommands.get(buildRedisKey(key));
        if (value == null) {
            LOGGER.infov("No author found for id : {0}", key);
            return Optional.empty();
        }
        return Optional.of(value);
    }

    void save(Author author) {
        authorCommands.set(buildRedisKey(author.id()), author, setArgs);
    }

    void del(String key) {
        try {
            authorCommands.getdel(buildRedisKey(key));
//            keyCommands.del(buildRedisKey(key)).onItem().onFailure().invoke(() -> LOGGER.error("Error deleting the key"));
//            keyCommands.del(buildRedisKey(key)).replaceWithVoid();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Uni<List<String>> keys() {
        return keyCommands.keys("*");
    }

    private String buildRedisKey(String id) {
        return "author_" + id;
    }
}

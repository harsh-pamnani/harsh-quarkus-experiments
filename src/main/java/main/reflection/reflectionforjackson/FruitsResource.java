package main.reflection.reflectionforjackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

/*
   IMPORTANT
   Uncomment @QuarkusMain from everywhere in the project to use the REST service

   1. Generate the native-image using "native" profile (just run clean install with native profile)
   2. That will generate the native image in target folder
   3. Run the native image using "./target/reflection-1.0.0-SNAPSHOT-runner main.reflection.Exp2"
   4. Notice that Fruit1 and Fruit2 are both exactly the same. But since Fruit1 is used in REST endpoints, Quarkus will register it for
      reflection automatically.
   5. Since Fruit2 is not used in REST endpoints, and it is not manually registered for reflection -> When we try to deserialize it using
      Jackson, it will throw an exception
   6. Just add @RegisterForReflection in Fruit2 and run the native image again. It should work now.
*/
@Path("/fruits")
public class FruitsResource {

    @Inject
    ObjectMapper objectMapper;

    private Set<Fruit1> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitsResource() {
        fruits.add(new Fruit1("Apple", "Winter fruit"));
        fruits.add(new Fruit1("Pineapple", "Tropical fruit"));
    }

    @GET
    public Set<Fruit1> list() throws JsonProcessingException {
        try {
            Fruit1 fruit1 = new Fruit1("some1", "desc1");
            System.out.println(objectMapper.writeValueAsString(fruit1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Fruit2 fruit2 = new Fruit2("some2", "desc2");
            System.out.println(objectMapper.writeValueAsString(fruit2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fruits;
    }

    @POST
    public Set<Fruit1> add(Fruit1 fruit1) {
        fruits.add(fruit1);
        return fruits;
    }

    @DELETE
    public Set<Fruit1> delete(Fruit1 fruit1) {
        fruits.removeIf(existingFruit1 -> existingFruit1.name().contentEquals(fruit1.name()));
        return fruits;
    }
}

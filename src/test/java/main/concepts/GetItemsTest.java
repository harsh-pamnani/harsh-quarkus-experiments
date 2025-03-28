package main.concepts;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.helpers.test.AssertSubscriber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetItemsTest {
    @Test
    void successTest() {
        Multi<Integer> itemsMulti = Multi.createFrom().items(10, 20);

        var result = itemsMulti.subscribe().withSubscriber(AssertSubscriber.create()).awaitCompletion().assertItems(10, 20);

        assertEquals(2, result.getItems().size());
    }

    @Test
    void failedTest() {
        Multi<Integer> itemsMulti = Multi.createFrom().items(10, 20);

        itemsMulti.subscribe().withSubscriber(AssertSubscriber.create()).awaitCompletion().assertItems(10, 20);
    }
}

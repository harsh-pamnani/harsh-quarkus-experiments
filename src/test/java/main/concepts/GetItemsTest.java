package main.concepts;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.helpers.test.AssertSubscriber;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GetItemsTest {
    // Since we are not providing any number when creating AssertSubscriber, it would fail at awaitCompletion
    @Test
    @Disabled
    void assertSubscriber_withoutAnyNumberInCreate_shouldFail() {
        Multi<Integer> itemsMulti = Multi.createFrom().items(10, 20);

        var result = itemsMulti.subscribe().withSubscriber(AssertSubscriber.create()).awaitCompletion().assertItems(10, 20);

        assertEquals(2, result.getItems().size());
    }

    // Since we have added `5` when creating AssertSubscriber, it would pass
    @Test
    void assertSubscriber_withNumberInCreateGreaterThanItems_shouldPass() {
        Multi<Integer> itemsMulti = Multi.createFrom().items(10, 20);

        var result = itemsMulti.subscribe().withSubscriber(AssertSubscriber.create(5)).awaitCompletion().assertItems(10, 20);

        assertEquals(2, result.getItems().size());
    }

    // Since we have added `2` when creating AssertSubscriber, it would pass
    // So the number in create should be greater or equal to the number of items we are expecting
    @Test
    void assertSubscriber_withNumberInCreateEqualToItems_shouldPass() {
        Multi<Integer> itemsMulti = Multi.createFrom().items(10, 20);

        var result = itemsMulti.subscribe().withSubscriber(AssertSubscriber.create(2)).awaitCompletion().assertItems(10, 20);

        assertEquals(2, result.getItems().size());
    }

    // Since we have added `1` when creating AssertSubscriber, it would fail at awaitCompletion because the multi wouldn't have completed as it has 1 more item
    @Test
    @Disabled
    void assertSubscriber_withNumberInCreateLessThanItems_shouldFail() {
        Multi<Integer> itemsMulti = Multi.createFrom().items(10, 20);

        var result = itemsMulti.subscribe().withSubscriber(AssertSubscriber.create(1)).awaitCompletion().assertItems(10);

        assertEquals(1, result.getItems().size());
    }
}

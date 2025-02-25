package main.mutiny;

import io.smallrye.mutiny.Uni;

import java.util.List;

public class UniListUniExample {
    public static void main(String[] args) {
        Uni<Void> uni1 = Uni.createFrom().item("first uni").onItem().invoke(s -> System.out.println(s)).replaceWithVoid();
        Uni<Void> uni2 = Uni.createFrom().item("second uni").onItem().invoke(s -> System.out.println(s)).replaceWithVoid();

        Uni<List<Uni<Void>>> updates = Uni.createFrom().item(List.of(uni1, uni2));

        // If we call `applyUpdates1` we won't get `first Uni` and `second Uni` messages because those unis are not consumed at all
        applyUpdates1(updates).onItem().invoke(() -> System.out.println("Update applied 1")).subscribe().with(
                item -> {},
                failure -> System.out.println("Error: " + failure)
        );

        // If we call `applyUpdates2` we will get `first Uni` and `second Uni` messages correctly.
        applyUpdates2(updates).onItem().invoke(() -> System.out.println("Update applied 2")).subscribe().with(
                item -> {},
                failure -> System.out.println("Error: " + failure)
        );
    }

    private static Uni<Void> applyUpdates1(Uni<List<Uni<Void>>> updates) {
        return Uni.join()
                  .all(updates)
                  .andCollectFailures()
                  .onFailure()
                  .invoke(error -> System.out.println("Failed to save event sync updates: " + error))
                  .replaceWithVoid();
    }

    private static Uni<Void> applyUpdates2(Uni<List<Uni<Void>>> updates) {
        return updates.flatMap(
                              updateList -> Uni.combine().all().unis(updateList).discardItems())
                      .onFailure()
                      .invoke(error -> System.out.println("Failed to save event sync updates: " + error))
                      .replaceWithVoid();
    }
}

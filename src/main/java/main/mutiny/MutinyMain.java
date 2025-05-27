package main.mutiny;

import io.smallrye.mutiny.Uni;

public class MutinyMain {
    // Difference between .call and .invoke
    // .invoke() is for calling methods with void return — result is ignored;
    // .call() is for calling methods with a Uni return — it gets subscribed and chained.

    public static void main(String[] args) {
        // Since we are using .call here, `handleFailure uni called` is printed.
        Uni<String> uni1 = Uni.createFrom().failure(new RuntimeException("dynamo error 1"));
        uni1.onFailure()
                .call(failure -> handleFailure(failure))
                .subscribe()
                .with(a -> System.out.println("SUCCESS : " + a));

        // Since we are using .invoke here, `handleFailure uni called` is NOT printed.
        Uni<String> uni2 = Uni.createFrom().failure(new RuntimeException("dynamo error 2"));
        uni2.onFailure()
                .invoke(failure -> handleFailure(failure))
                .subscribe()
                .with(a -> System.out.println("SUCCESS : " + a));
    }

    private static Uni<Void> handleFailure(Throwable failure) {
        System.out.println("FAILURE : " + failure.getMessage());
        return Uni.createFrom().voidItem().invoke(() -> System.out.println("handleFailure uni called"));
    }
}

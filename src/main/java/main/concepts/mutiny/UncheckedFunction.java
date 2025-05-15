package main.concepts.mutiny;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;

public class UncheckedFunction {
    public static void main(String[] args) {
        Uni.createFrom()
                .item("1")
                .map(Unchecked.function(UncheckedFunction::mightThrowCheckedException))
                .subscribe()
                .with(System.out::println, Throwable::printStackTrace);

        Uni.createFrom()
                .item("error")
                .map(Unchecked.function(UncheckedFunction::mightThrowCheckedException)) // Wraps checked exception
                .subscribe()
                .with(System.out::println, Throwable::printStackTrace);
    }

    static int mightThrowCheckedException(String s) throws Exception {
        if ("error".equals(s)) {
            throw new SomeErrorOccurredException("Something went wrong");
        }
        return Integer.parseInt(s);
    }
}

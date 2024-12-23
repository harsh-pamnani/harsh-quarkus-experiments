package main.concepts;

import com.github.f4b6a3.ulid.UlidCreator;
import java.util.UUID;

public class SortableUuid {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(SortableUuid.create());
        Thread.sleep(500);
        System.out.println(SortableUuid.create());
        Thread.sleep(500);
        System.out.println(SortableUuid.create());
        Thread.sleep(500);
        System.out.println(SortableUuid.create());
        Thread.sleep(500);
        System.out.println(SortableUuid.create());
    }

    public static UUID create() {
        return UlidCreator.getUlid().toUuid();
    }
}

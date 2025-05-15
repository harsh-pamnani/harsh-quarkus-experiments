package main.cdi.lock;

import io.quarkus.arc.Lock;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleBean {

    private int counter = 0;

    @Lock(Lock.Type.READ)
    public int getCounter() {
        return counter;
    }

    public int incrementCounterWithoutlock() {
        return ++counter;
    }

    @Lock
    public int incrementCounterWithLock() {
        return ++counter;
    }
}

package main.cdi.lock.exercise;

import io.quarkus.arc.Lock;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Exercise {

    private int counter = 15;

    public int getCounter() throws InterruptedException {
        Thread.sleep(10000);
        return counter;
    }
}
package main.cdi.injectusingannotations;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClassChildA implements ClassParent {
    @Override
    public void hello() {
        System.out.println("Hello from ClassChildA");
    }
}

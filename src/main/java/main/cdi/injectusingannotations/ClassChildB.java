package main.cdi.injectusingannotations;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RandomCustomizedQualifier
public class ClassChildB implements ClassParent {
    @Override
    public void hello() {
        System.out.println("Hello from ClassChildB");
    }
}

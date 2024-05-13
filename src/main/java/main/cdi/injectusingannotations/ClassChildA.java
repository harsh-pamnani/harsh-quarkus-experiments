package main.cdi.injectusingannotations;

import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ClassChildA implements ClassParent {
    private static final Logger LOGGER = Logger.getLogger(ClassChildA.class);

    @Override
    public void hello() {
        LOGGER.info("Hello from ClassChildA");
    }
}

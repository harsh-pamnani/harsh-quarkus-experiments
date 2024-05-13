package main.cdi.injectusingannotations;

import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
@RandomCustomizedQualifier
public class ClassChildB implements ClassParent {
    private static final Logger LOGGER = Logger.getLogger(ClassChildB.class);

    @Override
    public void hello() {
        LOGGER.info("Hello from ClassChildB");
    }
}

package main;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

class LoggingTest {
    private static final Logger LOGGER = Logger.getLogger(LoggingTest.class);

    @Test
    void testLogging() {
        LOGGER.log(Logger.Level.INFO, "This is an info message 1");
        LOGGER.info("This is an info message 2");

        LOGGER.log(Logger.Level.ERROR, "This is an error message");
    }
}

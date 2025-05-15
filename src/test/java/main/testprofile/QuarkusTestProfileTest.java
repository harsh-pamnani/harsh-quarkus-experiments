package main.testprofile;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

@JBossLog
@QuarkusTest
class QuarkusTestProfileTest {
    @ConfigProperty(name = "amazingProp")
    String amazingProp;

    @ConfigProperty(name = "awesomeProp")
    String awesomeProp;

    @Test
    void testWithoutOverride() {
        log.info("HP: amazingProp without override: " + amazingProp);
        log.info("HP: awesomeProp without override: " + awesomeProp);
    }
}

@TestProfile(AmazingPropOverrideTestProfile.class)
@JBossLog
@QuarkusTest
class QuarkusTestProfileWithOverrideTest {
    @ConfigProperty(name = "amazingProp")
    String amazingProp;

    @ConfigProperty(name = "awesomeProp")
    String awesomeProp;

    @Test
    void testWithOverride() {
        log.info("HP: amazingProp with override: " + amazingProp); // Value is overridden by test profile
        log.info("HP: awesomeProp without override: "
                + awesomeProp); // No override in test profile. So, value will be taken from props
    }
}

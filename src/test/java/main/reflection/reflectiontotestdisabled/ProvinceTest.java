package main.reflection.reflectiontotestdisabled;

import io.quarkus.test.junit.QuarkusTest;
import main.configsource.ConfigResource;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ProvinceTest {
    private static final Logger LOGGER = Logger.getLogger(ConfigResource.class);

    @ConfigProperty(name = "quarkus.profile")
    String quarkusProfile;

    @ConfigProperty(name = "my.propB")
    String myPropB;

    @Test
    void testIsDeprecated() {
        LOGGER.info(Province.isDeprecated("ON"));
        LOGGER.info(Province.isDeprecated("NY"));
    }

    @Test
    void testQuarkusProfile() {
        LOGGER.info("Quarkus profile : " + quarkusProfile);
        LOGGER.info("Test prop B : " + myPropB);

        assertThat(myPropB, is("Value B from app props for test profile"));
    }
}

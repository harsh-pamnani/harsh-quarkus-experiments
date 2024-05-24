package main.reflection.reflectiontotestdisabled;

import io.quarkus.test.junit.QuarkusTest;
import main.configsource.ConfigResource;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

@QuarkusTest
class ProvinceTest {
    private static final Logger LOGGER = Logger.getLogger(ConfigResource.class);

    @ConfigProperty(name = "quarkus.profile")
    String quarkusProfile;

    @Test
    void testIsDeprecated() {
        LOGGER.info(Province.isDeprecated("ON"));
        LOGGER.info(Province.isDeprecated("NY"));
    }

    @Test
    void testQuarkusProfile() {
        LOGGER.info("Quarkus profile : " + quarkusProfile);
    }
}

package main.reflection.reflectiontotestdisabled;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
class ProvinceTest {
    @ConfigProperty(name = "quarkus.profile")
    String quarkusProfile;

    @ConfigProperty(name = "my.propB")
    String myPropB;

    @Test
    void testIsDeprecated() {
        assertThat(Province.isDeprecated("ON"), is(false));
        assertThat(Province.isDeprecated("NY"), is(true));
    }

    @Test
    void testQuarkusProfile() {
        assertThat(quarkusProfile, is("test"));
        assertThat(myPropB, is("Value B from app props for test profile"));
    }
}

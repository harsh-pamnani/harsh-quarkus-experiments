package main.reflection.reflectiontotestdisabled;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProvinceTest {
    @Test
    public void testIsDeprecated() {
        System.out.println(Province.isDeprecated("ON"));
        System.out.println(Province.isDeprecated("NY"));
    }
}

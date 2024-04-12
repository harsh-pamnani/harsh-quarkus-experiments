package main;

import io.quarkus.test.junit.QuarkusTest;
import main.reflectiontotestdisabled.Province;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProvinceTest {
    @Test
    public void testIsDeprecated() {
        System.out.println(Province.isDeprecated("ON"));
        System.out.println(Province.isDeprecated("NY"));
    }
}

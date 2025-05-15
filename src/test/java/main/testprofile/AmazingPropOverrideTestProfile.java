package main.testprofile;

import io.quarkus.test.junit.QuarkusTestProfile;
import java.util.Map;

public class AmazingPropOverrideTestProfile implements QuarkusTestProfile {
    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of("amazingProp", "New overridden values from test profile");
    }
}

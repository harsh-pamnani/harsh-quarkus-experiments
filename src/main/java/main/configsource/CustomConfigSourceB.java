package main.configsource;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomConfigSourceB implements ConfigSource {
    private static final Map<String, String> configuration = new HashMap<>();

    static {
        configuration.put("my.propB", "Random value for B from custom config");
    }

    @Override
    public int getOrdinal() {
        return 220;
    }

    @Override
    public Set<String> getPropertyNames() {
        return configuration.keySet();
    }

    @Override
    public String getValue(final String propertyName) {
        return configuration.get(propertyName);
    }

    @Override
    public String getName() {
        return CustomConfigSourceB.class.getSimpleName();
    }
}
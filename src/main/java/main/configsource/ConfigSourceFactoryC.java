package main.configsource;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.OptionalInt;

import io.quarkus.runtime.configuration.ProfileManager;
import org.eclipse.microprofile.config.spi.ConfigSource;

import io.smallrye.config.ConfigSourceContext;
import io.smallrye.config.ConfigSourceFactory;
import io.smallrye.config.ConfigValue;
import io.smallrye.config.PropertiesConfigSource;

/**
 * Reference - https://quarkus.io/guides/config-extending-support#config-source-factory
 */
public class ConfigSourceFactoryC implements ConfigSourceFactory {
    @Override
    public Iterable<ConfigSource> getConfigSources(final ConfigSourceContext context) {
        // We have access to context which we don't have in ConfigResource.
        // final ConfigValue value = context.getValue("some.prop");
        if ("test".equalsIgnoreCase(ProfileManager.getActiveProfile()) || ProfileManager.getLaunchMode().isDevOrTest()) {
            return Collections.emptyList();
        }

        return Collections.singletonList(new CustomConfigSourceC());
    }

    @Override
    public OptionalInt getPriority() {
        return OptionalInt.of(275);
    }
}
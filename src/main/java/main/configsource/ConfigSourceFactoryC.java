package main.configsource;

import io.quarkus.runtime.configuration.ProfileManager;
import io.smallrye.config.ConfigSourceContext;
import io.smallrye.config.ConfigSourceFactory;
import java.util.Collections;
import java.util.OptionalInt;
import org.eclipse.microprofile.config.spi.ConfigSource;

/**
 * Reference - https://quarkus.io/guides/config-extending-support#config-source-factory
 */
public class ConfigSourceFactoryC implements ConfigSourceFactory {
    @Override
    public Iterable<ConfigSource> getConfigSources(final ConfigSourceContext context) {
        // We have access to context which we don't have in ConfigResource.
        // final ConfigValue value = context.getValue("some.prop");
        if ("test".equalsIgnoreCase(ProfileManager.getActiveProfile())
                || ProfileManager.getLaunchMode().isDevOrTest()) {
            return Collections.emptyList();
        }

        return Collections.singletonList(new CustomConfigSourceC());
    }

    @Override
    public OptionalInt getPriority() {
        return OptionalInt.of(275);
    }
}

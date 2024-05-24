package main.configsource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Path("/config")
public class ConfigResource {
    private static final Logger LOGGER = Logger.getLogger(ConfigResource.class);

    @ConfigProperty(name = "my.propA")
    String customPropertyA;

    @ConfigProperty(name = "my.propB")
    String customPropertyB;

    @ConfigProperty(name = "SECRET")
    String secretProperty;

    @Inject
    AppConfig appConfig;

    @GET
    @Path("/print-prop-values")
    public String getMyPropValue() {
        // propA from application.properties will be ignored because the custom config has ordinal 275 (vs 250 for application properties)
        LOGGER.infov("`my.propA` value : {0}", customPropertyA);

        // propB's value from custom config will be ignored because it has ordinal 220 (vs 250 for application properties)
        LOGGER.infov("`my.propB` value : {0}", customPropertyB);

        // SECRET from application.properties will be ignored app because will be taken from .env (as it has precedence over app props)
        LOGGER.infov("`SECRET` value : {0}", secretProperty);

        // xyz!api%key from env gets translated to `xyz.api-key` (because of ConfigMapping) and `xyz_api_key` for config properties. Print all to check
        LOGGER.infov("`apiKey` value : {0}", appConfig.apiKey());

        return "Check console for all properties";
    }

    private static void printAllProperties() {
        Config config = ConfigProvider.getConfig();

        Iterable<String> propertyNames = config.getPropertyNames();
        List<String> allProperties = StreamSupport.stream(propertyNames.spliterator(), false).toList();
        for (String prop: allProperties) {
            LOGGER.info("UNIQUE LOG : " + prop);
            if (prop.toLowerCase().startsWith("xyz")) {
                LOGGER.info("UNIQUE LOG xyz : " + config.getConfigValue(prop));
            }
        }
    }
}

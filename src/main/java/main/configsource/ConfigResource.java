package main.configsource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@Path("/config")
public class ConfigResource {
    private static final Logger LOGGER = Logger.getLogger(ConfigResource.class);

    @ConfigProperty(name = "my.propA")
    String customPropertyA;

    @ConfigProperty(name = "my.propB")
    String customPropertyB;

    @GET
    @Path("/print-prop-values")
    public String getMyPropValue() {
        // propA from application.properties will be ignored because the custom config has ordinal 275 (vs 250 for application properties)
        LOGGER.infov("`my.propA` value : {0}", customPropertyA);

        // propB's value from custom config will be ignored because it has ordinal 220 (vs 250 for application properties)
        LOGGER.infov("`my.propB` value : {0}", customPropertyB);

        return "Check console for all properties";
    }
}

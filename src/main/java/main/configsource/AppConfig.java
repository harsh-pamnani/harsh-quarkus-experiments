package main.configsource;

import io.smallrye.config.ConfigMapping;

/**
 * Reference https://quarkus.io/guides/config-reference#environment-variables
 *
 * PART-1
 * The @ConfigMapping annotation support the following naming strategies:
 * KEBAB_CASE (default) - The method name is derived by replacing case changes with a dash to map the configuration property.
 * VERBATIM - The method name is used as is to map the configuration property.
 * SNAKE_CASE - The method name is derived by replacing case changes with an underscore to map the configuration property.
 *
 * PART-2
 * Environment variables names follow the conversion rules specified by MicroProfile Config. Config searches three environment variables for a given property name (e.g. foo.BAR.baz):
 * foo.BAR.baz - Exact match
 * foo_BAR_baz - Replace each character that is neither alphanumeric nor _ with _
 * FOO_BAR_BAZ - Replace each character that is neither alphanumeric nor _ with _; then convert the name to upper case
 */
@ConfigMapping(prefix = "xyz")
public interface AppConfig {

    // Since we have added `apiKey` as method name in java, it will look for `xyz.api-key` property as per naming above
    // in PART-1
    // If it is in application.properties, it should be exact match - `xyz.api-key`. No alterations allowed.
    // If not, based on Part-2 documentation, first it will look for `xyz.api-key` env, then `xyz_api_key` env, and then
    // `XYZ_API_KEY` env
    String apiKey();
}

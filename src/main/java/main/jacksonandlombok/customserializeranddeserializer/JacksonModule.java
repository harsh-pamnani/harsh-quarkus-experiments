package main.jacksonandlombok.customserializeranddeserializer;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;

public class JacksonModule extends SimpleModule {
    public JacksonModule() {
        super("SomeRandomTempModuleName", Version.unknownVersion());
        addSerializer(LocalDateTime.class, new LocalDateTimeAdapterSerializer());
        addDeserializer(LocalDateTime.class, new LocalDateTimeAdapterDeserializer());
    }
}

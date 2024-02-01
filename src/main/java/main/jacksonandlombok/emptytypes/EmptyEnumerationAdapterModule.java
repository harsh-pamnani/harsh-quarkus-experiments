package main.jacksonandlombok.emptytypes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

public class EmptyEnumerationAdapterModule extends SimpleModule {
    private static final String NAME = "EmptyEnumerationAdapterModule";

    public EmptyEnumerationAdapterModule() {
        super(NAME, Version.unknownVersion());
        addSerializer((Class<Enumeration<?>>)(Class<?>)Enumeration.class, new EmptyEnumSerializer());
        addDeserializer((Class<Enumeration<?>>)(Class<?>)Enumeration.class, new EmptyEnumerationDeserializer());
    }

    public static class EmptyEnumSerializer extends JsonSerializer<Enumeration<?>> {

        @Override
        public void serialize(Enumeration<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value == null) {
                gen.writeStartObject();
                gen.writeEndObject();
            } else {
                provider.defaultSerializeValue(value, gen);
            }
        }
    }

    private static class EmptyEnumerationDeserializer extends JsonDeserializer<Enumeration<?>> {
        @Override
        public Enumeration<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            ObjectMapper mapper = (ObjectMapper) p.getCodec();
            if (p.isExpectedStartObjectToken()) {
                return (Enumeration<?>) mapper.readValues(p, Enumeration.class).readAll();
            } else {
                return Collections.emptyEnumeration();
            }
        }
    }
}

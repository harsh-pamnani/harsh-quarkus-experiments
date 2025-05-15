package main.jacksonandlombok.customserializeranddeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeAdapterDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        int year = node.get("date").get("year").asInt();
        int month = node.get("date").get("month").asInt();
        int day = node.get("date").get("day").asInt();

        int hour = node.get("time").get("hour").asInt();
        int minute = node.get("time").get("minute").asInt();
        int second = node.get("time").get("second").asInt();
        int nano = node.get("time").get("nano").asInt();

        return LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute, second, nano));
    }
}

package com.diploma.application.helpers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeStringToLocalDateDeserializer extends JsonDeserializer<LocalDate> implements java.io.Serializable {

    private static ZoneId ZONE_SWISS = ZoneId.of("Europe/Zurich");

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        String string = parser.getText().trim();
        if (string.length() == 0) {
            return null;
        }

        if (string.endsWith("Z")) {
            ZonedDateTime utcZonedDateTime = ZonedDateTime.parse(string);
            ZonedDateTime localDateTime = utcZonedDateTime.withZoneSameInstant(ZONE_SWISS);
            return localDateTime.toLocalDate();
        } else if (string.length() > 10) {
            return LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else {
            return LocalDate.parse(string);
        }
    }
}

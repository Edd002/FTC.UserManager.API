package com.fiap.tech.challenge.global.util.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StrictLongDeserializer extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken token = jsonParser.currentToken();
        if (!token.isNumeric()) {
            deserializationContext.reportInputMismatch(String.class, jsonParser.currentName() + " não é um valor do tipo `Long`.", token.toString());
            return null;
        }
        return jsonParser.getLongValue();
    }
}
package com.lp.domain.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.domain.model.Settings;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter
public class SettingsConverter implements AttributeConverter<Settings, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Settings attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Settings to JSON", e);
        }
    }

    @Override
    public Settings convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Settings.class);
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to Settings", e);
        }
    }
}

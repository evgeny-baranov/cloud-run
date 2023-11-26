package com.lp.domain.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.ArrayList;
import java.util.List;

public class StringListToJsonConverter implements AttributeConverter<List<String>, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error converting map to JSON string", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, ArrayList.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON string to map", e);
        }
    }
}

package com.lp.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
public class Settings {
    private final Map<String, Object> map = new HashMap<>();

    public void set(String key, String value) {
        map.put(key, value);
    }

    public void set(String key, Number value) {
        map.put(key, value);
    }

    public Optional<String> getString(String key) {
        Object value = map.get(key);
        if (value instanceof String) {
            return Optional.of((String) value);
        }
        return Optional.empty();
    }

    public Optional<Number> getNumber(String key) {
        Object value = map.get(key);
        if (value instanceof Number) {
            return Optional.of((Number) value);
        }
        return Optional.empty();
    }
}
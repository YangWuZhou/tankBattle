package com.sourcey.footBook.entity;

import java.util.HashMap;
import java.util.Map;

public class Info {
    private Map<Short, String> key;
    private Map<Short, String> value;

    public Info() {
        key = new HashMap<>();
        value = new HashMap<>();
    }


    public Map<Short, String> getKey() {
        return key;
    }

    public void addKey(Short keySize, String keyStr) {
        key.put(keySize, keyStr);
    }

    public Map<Short, String> getValue() {
        return value;
    }

    public void addValue(Short valueSize, String valueStr) {
        value.put(valueSize, valueStr);
    }

}

package com.sourcey.footBook.entity;

import java.util.HashMap;
import java.util.Map;

public class Info {

    private Map<String, String> payload;

    public Info() {
        payload = new HashMap<>();
    }

    public Map<String, String> getPayload() {
        return payload;
    }

    public String getValue(String key) {
        return payload.get(key);
    }

    public void add(String key, String value) {
        payload.put(key, value);
    }

}

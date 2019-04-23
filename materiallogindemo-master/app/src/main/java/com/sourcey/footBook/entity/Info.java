package com.sourcey.footBook.entity;

import java.util.HashMap;
import java.util.Map;

public class Info {
    private short keySize;
    private short valueSize;
    private String key;
    private String value;

    public Info() {
        keySize = 0;
        valueSize = 0;
        key = null;
        value = null;
    }

    public short getKeySize() {
        return keySize;
    }

    public void setKeySize(short keySize) {
        this.keySize = keySize;
    }

    public short getValueSize() {
        return valueSize;
    }

    public void setValueSize(short valueSize) {
        this.valueSize = valueSize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

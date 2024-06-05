package com.extraction.items;

public class Key extends Item {
    public static final int KEY_WEIGHT = 1;
    private String code;

    public Key(String code) {
        super("Key", KEY_WEIGHT, false);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
package com.beauver.Enums;

public enum ItemType {
    LANGUAGE("Programming Language"),
    FRAMEWORK("Framework"),
    TOOL("Tool"),
    OS("Operating System");

    private final String displayName;

    ItemType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

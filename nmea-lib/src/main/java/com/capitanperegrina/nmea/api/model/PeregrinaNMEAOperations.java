package com.capitanperegrina.nmea.api.model;

public enum PeregrinaNMEAOperations {
    LIST("LIST"),
    PARSE("PARSE"),
    RESET("RESET"),
    EXPORT("EXPORT");

    private String value;

    PeregrinaNMEAOperations(String value) {
        this.value = value;
    }
}

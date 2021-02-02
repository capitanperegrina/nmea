package com.capitanperegrina.nmea.api.model.enums;

public enum GPSOperationMode {

    MANUAL("M"),
    AUTOMATIC("A");

    private final String value;

    public String getValue() {
        return value;
    }

    GPSOperationMode(String value) {
        this.value = value;
    }
}

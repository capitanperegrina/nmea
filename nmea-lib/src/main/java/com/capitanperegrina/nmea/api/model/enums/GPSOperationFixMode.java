package com.capitanperegrina.nmea.api.model.enums;

public enum GPSOperationFixMode {

    NOT_AVAILABLE(1),

    FIX_2D(2),

    FIX_3D(3);

    private final int value;

    public int getValue() {
        return value;
    }

    GPSOperationFixMode(int value) {
        this.value = value;
    }
}

package com.capitanperegrina.nmea.api.model.beans.mapelements.elements;

public enum MapElementType {
    POINT(1),
    LINE(2);

    private final int value;

    MapElementType(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}


package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.segments;

public enum SevenSegment {
    SEGMENT_1(1), // top
    SEGMENT_2(2), // left top
    SEGMENT_3(3), // right top
    SEGMENT_4(4), // middle
    SEGMENT_5(5), // left bottom
    SEGMENT_6(6), // right bottom
    SEGMENT_7(7), // bottom
    SEGMENT_DOT(17), // dot
    SEGMENT_COMMA(18); // comma

    private final int value;

    SevenSegment(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

}

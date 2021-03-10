package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.segments;

public enum SevenSegment {
    SEGMENT_1(1), // top
    SEGMENT_2(2), // left top
    SEGMENT_3(3), // right top
    SEGMENT_4(4), // middle
    SEGMENT_5(5), // left bottom
    SEGMENT_6(6), // right bottom
    SEGMENT_7(7), // bottom
    SEGMENT_DOT(8), // dot
    SEGMENT_COMMA(9); // comma

    private final int value;

    SevenSegment(final int newValue) {
        this.value = newValue;
    }

    public int getValue() {
        return this.value;
    }

    public static SevenSegment getSevemSegmentSegment(final int i) {
        for (final SevenSegment ss : SevenSegment.values()) {
            if (ss.getValue() == i) {
                return ss;
            }
        }
        return null;
    }
}

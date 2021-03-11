package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.simpleseven.segments;

public enum SimpleSevenSegment {
    SEGMENT_1(1), // top
    SEGMENT_2(2), // left top
    SEGMENT_3(3), // right top
    SEGMENT_4(4), // middle
    SEGMENT_5(5), // left bottom
    SEGMENT_6(6), // right bottom
    SEGMENT_7(7), // bottom
    SEGMENT_DOT(8), // dot
    SEGMENT_COMMA(9), // comma
    SEGMENT_MINUS(10); // minus

    private final int value;

    SimpleSevenSegment(final int newValue) {
        this.value = newValue;
    }

    public int getValue() {
        return this.value;
    }

    public static SimpleSevenSegment getSevemSegmentSegment(final int i) {
        for (final SimpleSevenSegment ss : SimpleSevenSegment.values()) {
            if (ss.getValue() == i) {
                return ss;
            }
        }
        return null;
    }
}

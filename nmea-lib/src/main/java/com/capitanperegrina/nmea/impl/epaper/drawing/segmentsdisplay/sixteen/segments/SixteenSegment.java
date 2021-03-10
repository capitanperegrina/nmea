package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.segments;

public enum SixteenSegment {

    SEGMENT_1(1), // left top to top middle
    SEGMENT_2(2), // top middle to right top
    SEGMENT_3(3), // left top to left middle
    SEGMENT_4(4), // top left to middle middle
    SEGMENT_5(5), // top middle to middle middle
    SEGMENT_6(6), // top right to middle middle
    SEGMENT_7(7), // top middle to right middle
    SEGMENT_8(8), // left middle to middle middle
    SEGMENT_9(9), // middle middle to right middle
    SEGMENT_10(10), // left middle to bottom left
    SEGMENT_11(11),// left bottom to middle middle
    SEGMENT_12(12), // bottom middle to middle middle
    SEGMENT_13(13), // bottom right to middle middle
    SEGMENT_14(14), // right middle to bottom right
    SEGMENT_15(15), // bottpm left to bottom middle
    SEGMENT_16(16), // bottom middle to bottom right
    SEGMENT_DOT(17), // dot
    SEGMENT_COMMA(18); // comma

    private final int value;

    SixteenSegment(final int newValue) {
        this.value = newValue;
    }

    public int getValue() {
        return this.value;
    }

    public static SixteenSegment getSixteenSegment(final int i) {
        for (final SixteenSegment ss : SixteenSegment.values()) {
            if (ss.getValue() == i) {
                return ss;
            }
        }
        return null;
    }
}

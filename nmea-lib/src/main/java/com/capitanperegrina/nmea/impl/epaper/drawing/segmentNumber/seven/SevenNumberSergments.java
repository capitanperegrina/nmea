package com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.seven;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class SevenNumberSergments {

    private SevenNumberSergments() {
        // Instance creation not allowed as it's a static class
    }

    private static final Map<Integer, Set<SevenSegment>> numberSegments = new HashMap<>();

    static {
        //  ---
        // |   |
        //
        // |   |
        //  ---
        numberSegments.put(0, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.TOP, SevenSegment.LEFT_TOP, SevenSegment.RIGHT_TOP, SevenSegment.LEFT_BOTTOM, SevenSegment.RIGHT_BOTTOM, SevenSegment.BOTTOM})));

        //
        //     |
        //
        //     |
        //
        numberSegments.put(1, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.RIGHT_TOP, SevenSegment.RIGHT_BOTTOM})));

        //  ---
        //     |
        //  ---
        // |
        //  ---
        numberSegments.put(2, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.TOP, SevenSegment.RIGHT_TOP, SevenSegment.MIDDLE, SevenSegment.LEFT_BOTTOM, SevenSegment.BOTTOM})));

        //  ---
        //     |
        //  ---
        //     |
        //  ---
        numberSegments.put(3, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.TOP, SevenSegment.RIGHT_TOP, SevenSegment.MIDDLE, SevenSegment.RIGHT_BOTTOM, SevenSegment.BOTTOM})));

        //
        // |   |
        //  ---
        //     |
        //
        numberSegments.put(4, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.LEFT_TOP, SevenSegment.RIGHT_TOP, SevenSegment.MIDDLE, SevenSegment.RIGHT_BOTTOM})));

        //  ---
        // |
        //  ---
        //     |
        //  ---
        numberSegments.put(5, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.TOP, SevenSegment.LEFT_TOP, SevenSegment.MIDDLE, SevenSegment.RIGHT_BOTTOM, SevenSegment.BOTTOM})));

        //  ---
        // |
        //  ---
        // |   |
        //  ---
        numberSegments.put(6, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.TOP, SevenSegment.LEFT_TOP, SevenSegment.MIDDLE, SevenSegment.LEFT_BOTTOM, SevenSegment.RIGHT_BOTTOM, SevenSegment.BOTTOM})));

        //  ---
        //     |
        //
        //     |
        //
        numberSegments.put(7, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.TOP, SevenSegment.RIGHT_TOP, SevenSegment.RIGHT_BOTTOM})));

        //  ---
        // |   |
        //  ---
        // |   |
        //  ---
        numberSegments.put(8, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.TOP, SevenSegment.LEFT_TOP, SevenSegment.RIGHT_TOP, SevenSegment.MIDDLE, SevenSegment.LEFT_BOTTOM, SevenSegment.RIGHT_BOTTOM, SevenSegment.BOTTOM})));

        //  ---
        // |   |
        //  ---
        //     |
        //  ---
        numberSegments.put(8, new HashSet<SevenSegment>(Arrays.asList(new SevenSegment[] { SevenSegment.TOP, SevenSegment.LEFT_TOP, SevenSegment.RIGHT_TOP, SevenSegment.MIDDLE, SevenSegment.RIGHT_BOTTOM, SevenSegment.BOTTOM})));
    }

    public static Map<Integer, Set<SevenSegment>> getNumberSegments() {
        return numberSegments;
    }
}

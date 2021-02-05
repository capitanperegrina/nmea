package com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class NumberSergments {

    private NumberSergments() {
        // Instance creation not allowed as it's a static class
    }

    private static final Map<Integer, Set<Segment>> numberSegments = new HashMap<>();

    static {
        //  ---
        // |   |
        //
        // |   |
        //  ---
        numberSegments.put(0, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.TOP, Segment.LEFT_TOP, Segment.RIGHT_TOP, Segment.LEFT_BOTTOM, Segment.RIGHT_BOTTOM, Segment.BOTTOM})));

        //
        //     |
        //
        //     |
        //
        numberSegments.put(1, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.RIGHT_TOP, Segment.RIGHT_BOTTOM})));

        //  ---
        //     |
        //  ---
        // |
        //  ---
        numberSegments.put(2, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.TOP, Segment.RIGHT_TOP, Segment.MIDDLE, Segment.LEFT_BOTTOM, Segment.BOTTOM})));

        //  ---
        //     |
        //  ---
        //     |
        //  ---
        numberSegments.put(3, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.TOP, Segment.RIGHT_TOP, Segment.MIDDLE, Segment.RIGHT_BOTTOM, Segment.BOTTOM})));

        //
        // |   |
        //  ---
        //     |
        //
        numberSegments.put(4, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.LEFT_TOP, Segment.RIGHT_TOP, Segment.MIDDLE, Segment.RIGHT_BOTTOM})));

        //  ---
        // |
        //  ---
        //     |
        //  ---
        numberSegments.put(5, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.TOP, Segment.LEFT_TOP, Segment.MIDDLE, Segment.RIGHT_BOTTOM, Segment.BOTTOM})));

        //  ---
        // |
        //  ---
        // |   |
        //  ---
        numberSegments.put(6, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.TOP, Segment.LEFT_TOP, Segment.MIDDLE, Segment.LEFT_BOTTOM, Segment.RIGHT_BOTTOM, Segment.BOTTOM})));

        //  ---
        //     |
        //
        //     |
        //
        numberSegments.put(7, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.TOP, Segment.RIGHT_TOP, Segment.RIGHT_BOTTOM})));

        //  ---
        // |   |
        //  ---
        // |   |
        //  ---
        numberSegments.put(8, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.TOP, Segment.LEFT_TOP, Segment.RIGHT_TOP, Segment.MIDDLE, Segment.LEFT_BOTTOM, Segment.RIGHT_BOTTOM, Segment.BOTTOM})));

        //  ---
        // |   |
        //  ---
        //     |
        //  ---
        numberSegments.put(8, new HashSet<Segment>(Arrays.asList(new Segment[] { Segment.TOP, Segment.LEFT_TOP, Segment.RIGHT_TOP, Segment.MIDDLE, Segment.RIGHT_BOTTOM, Segment.BOTTOM})));
    }

    public static Map<Integer, Set<Segment>> getNumberSegments() {
        return numberSegments;
    }
}

package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.simpleseven.segments;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.PolygonType;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.SegmentComponent;
import org.javatuples.Pair;

import java.util.*;

public class SimpleSevenSergments {

    private static final Map<SimpleSevenSegment, List<SegmentComponent>> segmentPolygons = new HashMap<>();

    public static final Integer DIGIT_ZONE_WIDTH = 35;
    public static final Integer DIGIT_ZONE_HEIGHT = 46;

    static {

        //  ***
        // |   |
        //  ---
        // |   |
        //  ---
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_1, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_1).add(new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[]{new Pair<>(3, 1), new Pair<>(27, 3)})));

        //  ---
        // *   |
        //  ---
        // |   |
        //  ---
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_2, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_2).add(new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[]{new Pair<>(1, 3), new Pair<>(3, 22)})));

        //  ---
        // |   *
        //  ---
        // |   |
        //  ---
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_3, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_3).add(new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[]{new Pair<>(29, 3), new Pair<>(27, 22)})));

        //  ---
        // |   |
        //  ***
        // |   |
        //  ---
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_4, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_4).add(new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[]{new Pair<>(3, 22), new Pair<>(27, 24)})));

        //  ---
        // |   |
        //  ---
        // *   |
        //  ---
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_5, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_5).add(new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[]{new Pair<>(1, 24), new Pair<>(3, 43)})));

        //  ---
        // |   |
        //  ---
        // |   *
        //  ---
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_6, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_6).add(new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[]{new Pair<>(27, 24), new Pair<>(29, 43)})));

        //  ---
        // |   |
        //  ---
        // |   |
        //  ***
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_7, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_7).add(new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[]{new Pair<>(3, 43), new Pair<>(27, 45)})));

        // dot
        //  ---
        // |   |
        //  ---
        // |   |
        //  ---  .
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_DOT, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_DOT).add(new SegmentComponent(PolygonType.CIRCLE, Arrays.asList(new Pair[]{new Pair<>(31, 42), new Pair<>(1, null)})));

        // comma
        //  ---
        // |   |
        //  ---
        // |   |
        //  ---  ,
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_COMMA, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_COMMA).add(new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[]{new Pair<>(31, 43), new Pair<>(32, 43), new Pair<>(31, 45)})));
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_COMMA).add(new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[]{new Pair<>(31, 43), new Pair<>(30, 45), new Pair<>(31, 45)})));

        // minus
        //      ---
        //     |   |
        //  **  ---
        //     |   |
        //      ---  ,
        segmentPolygons.put(SimpleSevenSegment.SEGMENT_MINUS, new ArrayList<>());
        segmentPolygons.get(SimpleSevenSegment.SEGMENT_MINUS).add(new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[]{new Pair<>(-15, 22), new Pair<>(-3, 24)})));
    }

    private SimpleSevenSergments() {
        // Instance creation not allowed as it's a static class
    }

    public static List<SegmentComponent> getComponents(final SimpleSevenSegment segment) {
        return segmentPolygons.get(segment);
    }
}

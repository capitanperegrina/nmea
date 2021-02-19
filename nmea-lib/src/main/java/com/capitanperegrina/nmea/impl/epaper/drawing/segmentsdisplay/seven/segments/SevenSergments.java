package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.segments;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.PolygonType;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.SegmentComponent;
import org.javatuples.Pair;

import java.util.*;

public class SevenSergments {

    private static final Map<SevenSegment, List<SegmentComponent>> segmentPolygons = new HashMap<>();

    static {
        //  ***
        // |   |
        //  ---
        // |   |
        //  ---
        segmentPolygons.put(SevenSegment.SEGMENT_1, new ArrayList<>());
        segmentPolygons.get(SevenSegment.SEGMENT_1).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,2), new Pair<>(3,1), new Pair<>(3,3)})));
        segmentPolygons.get(SevenSegment.SEGMENT_1).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(3,1), new Pair<>(27,3)})));
        segmentPolygons.get(SevenSegment.SEGMENT_2).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(27,1), new Pair<>(28,2), new Pair<>(27,3)})));

        //  ---
        // *   |
        //  ---
        // |   |
        //  ---
        segmentPolygons.put(SevenSegment.SEGMENT_2, new ArrayList<>());
        segmentPolygons.get(SevenSegment.SEGMENT_2).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,2), new Pair<>(3,3), new Pair<>(1,3)})));
        segmentPolygons.get(SevenSegment.SEGMENT_2).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(1,3), new Pair<>(3,22)})));
        segmentPolygons.get(SevenSegment.SEGMENT_2).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(3,22), new Pair<>(2,23), new Pair<>(1,22)})));

        //  ---
        // |   *
        //  ---
        // |   |
        //  ---
        segmentPolygons.put(SevenSegment.SEGMENT_3, new ArrayList<>());
        segmentPolygons.get(SevenSegment.SEGMENT_3).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(28,2), new Pair<>(29,3), new Pair<>(27,3)})));
        segmentPolygons.get(SevenSegment.SEGMENT_3).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(29,3), new Pair<>(27,22)})));
        segmentPolygons.get(SevenSegment.SEGMENT_3).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(3,22), new Pair<>(2,23), new Pair<>(1,22)})));

        //  ---
        // |   |
        //  ***
        // |   |
        //  ---
        segmentPolygons.put(SevenSegment.SEGMENT_4, new ArrayList<>());
        segmentPolygons.get(SevenSegment.SEGMENT_4).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,23), new Pair<>(3,22), new Pair<>(3,24)})));
        segmentPolygons.get(SevenSegment.SEGMENT_4).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(3,22), new Pair<>(27,24)})));
        segmentPolygons.get(SevenSegment.SEGMENT_4).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(27,22), new Pair<>(28,23), new Pair<>(27,24)})));

        //  ---
        // |   |
        //  ---
        // *   |
        //  ---
        segmentPolygons.put(SevenSegment.SEGMENT_5, new ArrayList<>());
        segmentPolygons.get(SevenSegment.SEGMENT_5).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,23), new Pair<>(3,24), new Pair<>(1,24)})));
        segmentPolygons.get(SevenSegment.SEGMENT_5).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(1,24), new Pair<>(3,43)})));
        segmentPolygons.get(SevenSegment.SEGMENT_5).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(3,43), new Pair<>(2,44), new Pair<>(1,43)})));

        //  ---
        // |   |
        //  ---
        // |   *
        //  ---
        segmentPolygons.put(SevenSegment.SEGMENT_6, new ArrayList<>());
        segmentPolygons.get(SevenSegment.SEGMENT_6).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(27,24), new Pair<>(28,23), new Pair<>(29,24)})));
        segmentPolygons.get(SevenSegment.SEGMENT_6).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(27,24), new Pair<>(29,43)})));
        segmentPolygons.get(SevenSegment.SEGMENT_6).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(27,43), new Pair<>(28,44), new Pair<>(29,43)})));

        //  ---
        // |   |
        //  ---
        // |   |
        //  ***
        segmentPolygons.put(SevenSegment.SEGMENT_7, new ArrayList<>());
        segmentPolygons.get(SevenSegment.SEGMENT_7).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,44), new Pair<>(3,43), new Pair<>(3,45)})));
        segmentPolygons.get(SevenSegment.SEGMENT_7).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(3,43), new Pair<>(27,45)})));
        segmentPolygons.get(SevenSegment.SEGMENT_7).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(27,43), new Pair<>(28,44), new Pair<>(27,45)})));

        // dot
        //  ---
        // |   |
        //  ---
        // |   |
        //  ---  .
        segmentPolygons.put(SevenSegment.SEGMENT_DOT, new ArrayList<>());
        segmentPolygons.get(SevenSegment.SEGMENT_DOT).add( new SegmentComponent(PolygonType.CIRCLE, Arrays.asList(new Pair[] {new Pair<>(30,42), new Pair<>(1,null)})));

        // comma
        //  ---
        // |   |
        //  ---
        // |   |
        //  ---  ,
        segmentPolygons.put(SevenSegment.SEGMENT_COMMA, new ArrayList<>());
        segmentPolygons.get(SevenSegment.SEGMENT_COMMA).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(30,43), new Pair<>(31,43), new Pair<>(30,45)})));
        segmentPolygons.get(SevenSegment.SEGMENT_COMMA).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(30,43), new Pair<>(29,45), new Pair<>(30,45)})));
    }

    private SevenSergments() {
        // Instance creation not allowed as it's a static class
    }

    public static List<SegmentComponent> getComponents(SevenSegment segment) {
        return segmentPolygons.get(segment);
    }
}

package com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.sixteen.segments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Pair;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.sixteen.segments.components.PolygonType;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.sixteen.segments.components.SegmentComponent;

public class SixteenSegments {

    private static final Map<SixteenSegment, List<SegmentComponent>> segmentPolygons = new HashMap<>();

    static {
        // left top to top middle
        //  * -
        // |\|/|
        //  - -
        // |/|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_1, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_1).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,2), new Pair<>(3,1), new Pair<>(3,3)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_1).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(3,1), new Pair<>(15,2)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_1).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(14,1), new Pair<>(15,2), new Pair<>(14,3)})));

        // top middle to right top
        //  - *
        // |\|/|
        //  - -
        // |/|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_2, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_2).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,2), new Pair<>(3,1), new Pair<>(3,3)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_2).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(16,1), new Pair<>(27,3)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_2).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(27,1), new Pair<>(28,2), new Pair<>(27,3)})));

        // left top to left middle
        //  - -
        // *\|/|
        //  - -
        // |/|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_3, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_3).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,2), new Pair<>(3,3), new Pair<>(1,3)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_3).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(1,3), new Pair<>(3,22)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_3).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(3,22), new Pair<>(2,23), new Pair<>(1,22)})));

        // top left to middle middle
        //  - -
        // |*|/|
        //  - -
        // |/|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_4, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_4).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(4,4), new Pair<>(5,4), new Pair<>(4,6)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_4).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(5,4), new Pair<>(4,6), new Pair<>(13,19)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_4).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(13,19), new Pair<>(13,21), new Pair<>(12,21)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_4).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(13,19), new Pair<>(13,21), new Pair<>(4,6)})));

        // top middle to middle middle
        //  - -
        // |\*/|
        //  - -
        // |/|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_5, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_5).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(15,2), new Pair<>(14,3), new Pair<>(16,3)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_5).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(14,3), new Pair<>(16,22)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_5).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(14,22), new Pair<>(15,23), new Pair<>(16,22)})));

        // top right to middle middle
        //  - -
        // |\|*|
        //  - -
        // |/|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_6, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_6).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(25,4), new Pair<>(26,4), new Pair<>(26,6)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_6).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(26,4), new Pair<>(26,6), new Pair<>(18,21)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_6).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(18,21), new Pair<>(17,19), new Pair<>(24,5)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_6).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(18,21), new Pair<>(17,21), new Pair<>(17,19)})));

        // top middle to right middle
        //  - -
        // |\|/*
        //  - -
        // |/|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_7, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_7).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(28,2), new Pair<>(29,3), new Pair<>(27,3)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_7).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(29,3), new Pair<>(27,22)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_7).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(3,22), new Pair<>(2,23), new Pair<>(1,22)})));

        // left middle to middle middle
        //  - -
        // |\|/|
        //  * -
        // |/|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_8, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_8).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,23), new Pair<>(3,22), new Pair<>(3,24)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_8).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(3,22), new Pair<>(14,24)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_8).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(14,22), new Pair<>(15,23), new Pair<>(14,24)})));

        // middle middle to right middle
        //  - -
        // |\|/|
        //  - *
        // |/|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_9, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_9).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(15,23), new Pair<>(16,22), new Pair<>(16,24)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_9).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(16,22), new Pair<>(27,24)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_9).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(27,22), new Pair<>(28,23), new Pair<>(27,24)})));


        // left middle to bottom left
        //  - -
        // |\|/|
        //  - -
        // */|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_10, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_10).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(2,23), new Pair<>(3,24), new Pair<>(1,24)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_10).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(1,24), new Pair<>(3,43)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_10).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(3,43), new Pair<>(2,44), new Pair<>(1,43)})));

        // left bottom to middle middle
        //  - -
        // |\|/|
        //  - -
        // |*|\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_11, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_11).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(12,25), new Pair<>(13,25), new Pair<>(13,27)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_11).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(12,25), new Pair<>(13,27), new Pair<>(5,42)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_11).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(4,40), new Pair<>(5,42), new Pair<>(12,25)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_11).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(4,40), new Pair<>(4,42), new Pair<>(5,42)})));

        // bottom middle to middle middle
        //  - -
        // |\|/|
        //  - -
        // |/*\|
        //  - -  ;
        segmentPolygons.put(SixteenSegment.SEGMENT_12, new ArrayList<>());
        segmentPolygons.get(SixteenSegment.SEGMENT_12).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(15,23), new Pair<>(16,24), new Pair<>(14,24)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_12).add( new SegmentComponent(PolygonType.RECTANGLE, Arrays.asList(new Pair[] {new Pair<>(16,24), new Pair<>(14,43)})));
        segmentPolygons.get(SixteenSegment.SEGMENT_12).add( new SegmentComponent(PolygonType.TRIANGLE, Arrays.asList(new Pair[] {new Pair<>(16,43), new Pair<>(15,44), new Pair<>(14,43)})));

        // bottom right to middle middle
        //  - -
        // |\|/|
        //  - -
        // |/|*|
        //  - -  ;
        // paintSegment(graphics, lightedSegments.contains(13), getPoints("17,25;18,25;26,40;26,42;25,42;17,27;17,25"));

        // right middle to bottom right
        //  - -
        // |\|/|
        //  - -
        // |/|\*
        //  - -  ;
        // paintSegment(graphics, lightedSegments.contains(14), getPoints("28,23;29,24;29,43;28,44;27,43;27,24;28,23"));

        // bottpm left to bottom middle
        //  - -
        // |\|/|
        //  - -
        // |/|\|
        //  * -  ;
        // paintSegment(graphics, lightedSegments.contains(15), getPoints("2,44;3,43;14,43;15,4;14,45;3,45;2,44"));

        // bottom middle to bottom right
        //  - -
        // |\|/|
        //  - -
        // |/|\|
        //  - *  ;
        // paintSegment(graphics, lightedSegments.contains(16), getPoints("15,244;16,43;27,43;28,44;27,45;16,45;15,44"));

        // dot
        //  - -
        // |\|/|
        //  - -
        // |/|\|
        //  - -  *

        // comma
        //  - -
        // |\|/|
        //  - -
        // |/|\|
        //  - -  *

        // blank
        //  - -
        // |\|/|
        //  - -
        // |/|\|
        //  - -  ;


    }

    private SixteenSegments() {
        // Instance creation not allowed as it's static class.
    }

    public static List<SegmentComponent> getComponents(SixteenSegment segment) {
        return segmentPolygons.get(segment);
    }
}

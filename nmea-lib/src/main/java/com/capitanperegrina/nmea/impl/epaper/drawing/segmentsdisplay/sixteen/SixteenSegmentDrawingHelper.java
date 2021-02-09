package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.SegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.SegmentComponent;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.naming.SegmentsNaming;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.segments.SixteenSegment;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.segments.SixteenSegments;
import org.javatuples.Pair;
import tk.schmid.epaper.display.EPaperDisplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SixteenSegmentDrawingHelper extends SegmentDrawingHelper {

    public SixteenSegmentDrawingHelper(EPaperDisplay screen) {
        super(screen);
    }

    public Pair<Integer,Integer> drawCharacter(Pair<Integer,Integer> offsetStart, char character, int scale) {
        List<Integer> segments = Arrays.asList( SixteenSegmentAlfabet.valueOf(""+character).getSegments());
        List<SegmentComponent> characterSegmentComponents = new ArrayList<>();
        for ( Integer idSegment : segments ) {
            characterSegmentComponents.addAll( SixteenSegments.getComponents(SixteenSegment.valueOf(idSegment.toString()) ));
        }
        this.drawComponents(offsetStart, characterSegmentComponents,scale);
        return new Pair<>(offsetStart.getValue0()+ SegmentsNaming.SEGMENT_CHAR_WIDTH*scale,offsetStart.getValue1()+SegmentsNaming.SEGMENT_CHAR_HEIGHT*scale);
    }
}

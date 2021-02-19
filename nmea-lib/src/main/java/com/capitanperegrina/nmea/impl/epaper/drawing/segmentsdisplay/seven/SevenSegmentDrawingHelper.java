package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven;

import java.util.*;
import java.util.stream.Collectors;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.SegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.SegmentComponent;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.naming.SegmentsNaming;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.segments.SevenSegment;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.segments.SevenSergments;
import org.javatuples.Pair;
import tk.schmid.epaper.display.EPaperDisplay;

public class SevenSegmentDrawingHelper extends SegmentDrawingHelper {

    public SevenSegmentDrawingHelper(EPaperDisplay screen) {
        super(screen);
    }

    public Pair<Integer,Integer> drawCharacter(Pair<Integer,Integer> offsetStart, char character, int scale) {
        List<Integer> segments = SevenSegmentsAlphabet.getSegments(character);
        List<SegmentComponent> characterSegmentComponents = new ArrayList<>();
        for ( Integer idSegment : segments ) {
            characterSegmentComponents.addAll( SevenSergments.getComponents(SevenSegment.valueOf(idSegment.toString()) ));
        }
        this.drawComponents(offsetStart, characterSegmentComponents,scale);
        return new Pair<>(offsetStart.getValue0()+SegmentsNaming.SEGMENT_CHAR_WIDTH*scale,offsetStart.getValue1()+SegmentsNaming.SEGMENT_CHAR_HEIGHT*scale);
    }
}

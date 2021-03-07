package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven;

import java.util.*;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.SegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.SegmentComponent;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.naming.SegmentsNaming;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.segments.SevenSegment;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.segments.SevenSergments;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.SixteenSegmentAlfabet;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.segments.SixteenSegments;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.segments.SpecialCharsAlphabet;
import org.javatuples.Pair;
import tk.schmid.epaper.display.EPaperDisplay;
import tk.schmid.epaper.display.protocol.DisplayColor;

public class SevenSegmentDrawingHelper extends SegmentDrawingHelper {

    public SevenSegmentDrawingHelper(EPaperDisplay screen) {
        super(screen);
    }

    private List<Integer> specialToSegments(SpecialCharsAlphabet specialChar) {
        if (specialChar == SpecialCharsAlphabet.COMMA) {
            return Arrays.asList(SixteenSegmentAlfabet.valueOf(",").getSegments());
        }
        return Arrays.asList(SixteenSegmentAlfabet.valueOf(".").getSegments());
    }

    public Pair<Integer, Integer> drawCharacter(Pair<Integer, Integer> offsetStart, char character, SpecialCharsAlphabet specialChar, int scale) {
        List<Integer> segments = SevenSegmentsAlphabet.getSegments(character);
        segments.addAll(specialToSegments(specialChar));
        return doDrawCharacter(offsetStart, segments, scale);
    }

    private Pair<Integer, Integer> doDrawCharacter(Pair<Integer, Integer> offsetStart, List<Integer> segments, int scale) {
        List<SegmentComponent> characterSegmentComponents = new ArrayList<>();
        for (Integer idSegment : segments) {
            characterSegmentComponents.addAll(SevenSergments.getComponents(SevenSegment.valueOf(idSegment.toString())));
        }
        this.drawComponents(offsetStart, characterSegmentComponents, scale);
        return new Pair<>(offsetStart.getValue0() + SegmentsNaming.SEGMENT_CHAR_WIDTH * scale, offsetStart.getValue1() + SegmentsNaming.SEGMENT_CHAR_HEIGHT * scale);
    }

    public void clearZoneForDigit(Pair<Integer, Integer> offsetStart, int scale) {
        this.screen.setColor(DisplayColor.White, DisplayColor.White);
        clearZone(offsetStart, new Pair<>(offsetStart.getValue0() + SevenSergments.DIGIT_ZONE_WIDTH * scale, offsetStart.getValue0() + SevenSergments.DIGIT_ZONE_HEIGHT * scale));
        this.screen.setColor(DisplayColor.Black, DisplayColor.White);
    }
}

package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.SegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.SegmentComponent;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.naming.SegmentsNaming;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.naming.SpecialCharsAlphabet;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.segments.SevenSegment;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.segments.SevenSergments;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.SixteenSegmentAlfabet;
import org.javatuples.Pair;
import tk.schmid.epaper.display.EPaperDisplay;
import tk.schmid.epaper.display.protocol.DisplayColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SevenSegmentDrawingHelper extends SegmentDrawingHelper {

    public SevenSegmentDrawingHelper(final EPaperDisplay screen) {
        super(screen);
    }

    @Override
    public void clearCharacter(final Pair<Integer, Integer> offsetStart, final int scale) {
        this.screen.setColor(DisplayColor.White, DisplayColor.White);
        this.screen.drawRectangle(offsetStart.getValue0(), offsetStart.getValue1(), offsetStart.getValue0() + SevenSergments.DIGIT_ZONE_WIDTH * scale, offsetStart.getValue1() + SevenSergments.DIGIT_ZONE_HEIGHT * scale, true);
        this.screen.setColor(DisplayColor.Black, DisplayColor.White);
    }

    private List<Integer> specialToSegments(final SpecialCharsAlphabet specialChar) {
        if (specialChar == null) {
            return new ArrayList();
        }
        if (specialChar == SpecialCharsAlphabet.COMMA) {
            return Arrays.asList(SevenSegmentsAlphabet.getSegments(SpecialCharsAlphabet.COMMA.asChar()));
        }
        return Arrays.asList(SevenSegmentsAlphabet.getSegments(SpecialCharsAlphabet.DOT.asChar()));
    }

    @Override
    public Pair<Integer, Integer> drawCharacter(final Pair<Integer, Integer> offsetStart, final char character, final SpecialCharsAlphabet specialChar, final int scale) {
        final List<Integer> segments = new ArrayList<>();
        segments.addAll(Arrays.asList(SevenSegmentsAlphabet.getSegments(character)));
        segments.addAll(this.specialToSegments(specialChar));

        return this.doDrawCharacter(offsetStart, segments, scale);
    }

    private Pair<Integer, Integer> doDrawCharacter(final Pair<Integer, Integer> offsetStart, final List<Integer> segments, final int scale) {
        final List<SegmentComponent> characterSegmentComponents = new ArrayList<>();
        for (final Integer idSegment : segments) {
            characterSegmentComponents.addAll(SevenSergments.getComponents(SevenSegment.getSevemSegmentSegment(idSegment)));
        }
        this.drawComponents(offsetStart, characterSegmentComponents, scale);
        return new Pair<>(offsetStart.getValue0() + SegmentsNaming.SEGMENT_CHAR_WIDTH * scale, offsetStart.getValue1());
    }

    @Override
    public void clearZoneForDigit(final Pair<Integer, Integer> offsetStart, final int scale) {
        this.screen.setColor(DisplayColor.White, DisplayColor.White);
        this.clearZone(offsetStart, new Pair<>(offsetStart.getValue0() + SevenSergments.DIGIT_ZONE_WIDTH * scale, offsetStart.getValue0() + SevenSergments.DIGIT_ZONE_HEIGHT * scale));
        this.screen.setColor(DisplayColor.Black, DisplayColor.White);
    }
}

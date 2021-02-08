package com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.sixteen;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.SegmentDrawingHelper;
import org.javatuples.Pair;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

public class SixteenSegmentDrawingHelper extends SegmentDrawingHelper {

    public SixteenSegmentDrawingHelper(SerialEPaperDisplay screen, Integer characterHeight, Integer characterWidth) {
        super(screen,characterHeight,characterWidth);
    }

    @Override
    public Pair<Integer, Integer> drawCharacter(Pair<Integer, Integer> offset, char character, int scale) {


        return null;
    }
}

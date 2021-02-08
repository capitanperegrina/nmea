package com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber;

import org.javatuples.Pair;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

public abstract class SegmentDrawingHelper {

    protected SerialEPaperDisplay screen;
    protected Integer characterHeight;
    protected Integer characterWidth;

    public SegmentDrawingHelper(SerialEPaperDisplay screen, Integer characterHeight, Integer characterWidth) {
        this.screen = screen;
        this.characterHeight = characterHeight;
        this.characterWidth = characterWidth;
    }

    public abstract Pair<Integer,Integer> drawCharacter(Pair<Integer,Integer> offset, char character, int scale);
}

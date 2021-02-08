package com.capitanperegrina.nmea.impl.epaper.drawing;

import org.javatuples.Pair;
import tk.schmid.epaper.display.EPaperDisplay;

public abstract class DrawingHelper {

    protected final EPaperDisplay screen;

    public DrawingHelper(EPaperDisplay screen) {
        this.screen = screen;
    }

    public EPaperDisplay getScreen() {
        return screen;
    }

    public abstract Pair<Integer, Integer> drawString(Pair<Integer, Integer> offsetStart, String text, int scale);
}

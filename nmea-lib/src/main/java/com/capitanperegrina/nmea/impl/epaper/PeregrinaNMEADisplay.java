package com.capitanperegrina.nmea.impl.epaper;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.epaper.drawing.DrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.SevenSegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.SixteenSegmentDrawingHelper;
import tk.schmid.epaper.display.EPaperDisplay;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

import java.text.DecimalFormat;

import org.javatuples.Pair;

public class PeregrinaNMEADisplay {

    private static volatile PeregrinaNMEADisplay singleton;

    private EPaperDisplay ePaperDisplay;
    private SixteenSegmentDrawingHelper sixteenSegmentDrawingHelper;
    private SevenSegmentDrawingHelper sevenSegmentDrawingHelper;

    private PeregrinaNMEADisplay(){
        if (singleton != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static PeregrinaNMEADisplay getInstance() {
        if (singleton == null) {
            synchronized (PeregrinaNMEADisplay.class) {
                if (singleton == null) singleton = new PeregrinaNMEADisplay();
            }
        }
        return singleton;
    }

    public void configure(PeregrinaNMEAExcutionParameters params) {
        if (singleton == null) {
            synchronized (PeregrinaNMEADisplay.class) {
                this.ePaperDisplay = new SerialEPaperDisplay(params.getSerialPortName());
                this.sixteenSegmentDrawingHelper = new SixteenSegmentDrawingHelper(this.ePaperDisplay);
                this.sevenSegmentDrawingHelper = new SevenSegmentDrawingHelper(this.ePaperDisplay);
                this.ePaperDisplay.connect();
                System.out.println("Connected...");
                System.out.println("Active storage: " + this.ePaperDisplay.getActiveStorage());
                System.out.println("Display Direction: " + this.ePaperDisplay.getDisplayDirection());
                System.out.println("English Font Size: " + this.ePaperDisplay.getEnglishFontSize());
                System.out.println("Chinese Font Size " + this.ePaperDisplay.getChineseFontSize());
                System.out.println("Drawing Color: " + this.ePaperDisplay.getDrawingColor());
                System.out.println("Background Color: " + this.ePaperDisplay.getBackgroundColor());
                System.out.println("Baud Rate: " + this.ePaperDisplay.getBaudRate());
            }
        }
    }

    public EPaperDisplay getePaperDisplay() {
        return this.ePaperDisplay;
    }

    public Pair<Integer,Integer> draw16segments(Pair<Integer, Integer> startOffset, int scale, String text) {
        return this.sixteenSegmentDrawingHelper.drawString(startOffset,text,scale);
    }

    public Pair<Integer,Integer> draw7segments(Pair<Integer, Integer> startOffset, int scale, String text) {
        return this.sevenSegmentDrawingHelper.drawString(startOffset,text,scale);
    }

    public void clearScreen() {
        if ( this.ePaperDisplay != null ) {
            this.ePaperDisplay.clearScreen();
        }
    }
}
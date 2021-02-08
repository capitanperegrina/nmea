package com.capitanperegrina.nmea.impl.epaper;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.seven.SevenSegmentDrawingHelper;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

import java.text.DecimalFormat;

public class PeregrinaNMEADisplay {

    private static volatile PeregrinaNMEADisplay singleton;
    private static final int CHARS_IN_DISPLAY = 3;

    private SevenSegmentDrawingHelper drawingHelper;

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
                this.drawingHelper = new SevenSegmentDrawingHelper(
                        new SerialEPaperDisplay(params.getScreenSerialPortName()),
                        params.getScreenHeight()/(CHARS_IN_DISPLAY),
                        params.getScreenWidth()/(CHARS_IN_DISPLAY+1));
                this.drawingHelper.getScreen().connect();
                System.out.println("Connected...");
                System.out.println("Active storage: " + this.drawingHelper.getScreen().getActiveStorage());
                System.out.println("Display Direction: " + this.drawingHelper.getScreen().getDisplayDirection());
                System.out.println("English Font Size: " + this.drawingHelper.getScreen().getEnglishFontSize());
                System.out.println("Chinese Font Size " + this.drawingHelper.getScreen().getChineseFontSize());
                System.out.println("Drawing Color: " + this.drawingHelper.getScreen().getDrawingColor());
                System.out.println("Background Color: " + this.drawingHelper.getScreen().getBackgroundColor());
                System.out.println("Baud Rate: " + this.drawingHelper.getScreen().getBaudRate());
            }
        }
    }

    public SerialEPaperDisplay getScreen() {
        return this.drawingHelper.getScreen();
    }

    public void draw(Integer xOffset, Integer yOffset, Float floatNumber) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        df.format(floatNumber).chars().mapToObj(character -> this.drawingHelper.drawCharacter(xOffset, yOffset, (char) character));
    }

    public void draw(Integer xOffset, Integer yOffset, Double doubleNumber) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        df.format(doubleNumber).chars().mapToObj(character -> this.drawingHelper.drawCharacter(xOffset, yOffset, (char) character));
    }

    public void clearScreen() {
        if ( this.drawingHelper.getScreen() != null ) {
            this.drawingHelper.getScreen().clearScreen();
        }
    }
}
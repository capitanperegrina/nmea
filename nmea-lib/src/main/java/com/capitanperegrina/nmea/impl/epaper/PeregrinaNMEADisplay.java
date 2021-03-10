package com.capitanperegrina.nmea.impl.epaper;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.SevenSegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.SixteenSegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.model.impl.TrackPointDaoImpl;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.schmid.epaper.display.EPaperDisplay;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

public class PeregrinaNMEADisplay {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackPointDaoImpl.class);

    private static volatile PeregrinaNMEADisplay singleton;

    private EPaperDisplay ePaperDisplay;
    private SixteenSegmentDrawingHelper sixteenSegmentDrawingHelper;
    private SevenSegmentDrawingHelper sevenSegmentDrawingHelper;

    private PeregrinaNMEADisplay() {
        if (singleton != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static PeregrinaNMEADisplay getInstance() {
        if (singleton == null) {
            synchronized (PeregrinaNMEADisplay.class) {
                if (singleton == null) {
                    singleton = new PeregrinaNMEADisplay();
                }
            }
        }
        return singleton;
    }

    public void configure(final PeregrinaNMEAExcutionParameters params) {
        if (singleton != null) {
            synchronized (PeregrinaNMEADisplay.class) {
                this.ePaperDisplay = new SerialEPaperDisplay(params.getScreenSerialPortName());
                this.sixteenSegmentDrawingHelper = new SixteenSegmentDrawingHelper(this.ePaperDisplay);
                this.sevenSegmentDrawingHelper = new SevenSegmentDrawingHelper(this.ePaperDisplay);
                this.ePaperDisplay.connect();
                LOGGER.info("Connected...");
                this.clearAndRepaintScreen();
                // LOGGER.info("Active storage: " + this.ePaperDisplay.getActiveStorage());
                // LOGGER.info("Display Direction: " + this.ePaperDisplay.getDisplayDirection());
                // LOGGER.info("English Font Size: " + this.ePaperDisplay.getEnglishFontSize());
                // LOGGER.info("Chinese Font Size " + this.ePaperDisplay.getChineseFontSize());
                // LOGGER.info("Drawing Color: " + this.ePaperDisplay.getDrawingColor());
                // LOGGER.info("Background Color: " + this.ePaperDisplay.getBackgroundColor());
                // LOGGER.info("Baud Rate: " + this.ePaperDisplay.getBaudRate());
            }
        }
    }

    public EPaperDisplay getePaperDisplay() {
        return this.ePaperDisplay;
    }

    public Pair<Integer, Integer> draw16segments(final Pair<Integer, Integer> startOffset, final int scale, final String text) {
        return this.sixteenSegmentDrawingHelper.drawString(startOffset, text, scale);
    }

    public Pair<Integer, Integer> draw16segments(final Pair<Integer, Integer> startOffset, final int scale, final Double d) {
        return this.sixteenSegmentDrawingHelper.drawDouble(startOffset, d, scale);
    }

    public Pair<Integer, Integer> draw7segments(final Pair<Integer, Integer> startOffset, final int scale, final String text) {
        return this.sevenSegmentDrawingHelper.drawString(startOffset, text, scale);
    }

    public Pair<Integer, Integer> draw7segments(final Pair<Integer, Integer> startOffset, final int scale, final Double d) {
        return this.sevenSegmentDrawingHelper.drawDouble(startOffset, d, scale);
    }

    public void clearScreen() {
        if (this.ePaperDisplay != null) {
            this.ePaperDisplay.clearScreen();
        }
    }

    public void clearAndRepaintScreen() {
        if (this.ePaperDisplay != null) {
            this.ePaperDisplay.clearScreen();
            this.ePaperDisplay.repaint();
        }
    }

    public void splashScreen() {
        // Show splash image
        this.clearScreen();
        this.ePaperDisplay.displayImage(400, 300, "SPLASH.BMP");
        this.ePaperDisplay.repaint();

        // Wait 2 seconds.
        try {
            Thread.sleep(3000);
        } catch (final InterruptedException e) {
            LOGGER.info(e.getMessage());
        }

        // Clear screen
        this.clearScreen();
        this.ePaperDisplay.repaint();
    }
}
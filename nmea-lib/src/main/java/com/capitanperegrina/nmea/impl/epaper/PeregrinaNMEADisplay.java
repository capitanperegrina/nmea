package com.capitanperegrina.nmea.impl.epaper;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven.SevenSegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.SixteenSegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.model.impl.TrackPointDaoImpl;
import com.capitanperegrina.nmea.impl.utils.GeoUtils;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.schmid.epaper.display.EPaperDisplay;
import tk.schmid.epaper.display.protocol.DisplayColor;
import tk.schmid.epaper.display.protocol.DisplayFontSize;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

public class PeregrinaNMEADisplay {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackPointDaoImpl.class);

    private enum ActiveScreenType {NO_WAYPOINT, TO_WAYPOINT}

    private static volatile PeregrinaNMEADisplay singleton;

    private EPaperDisplay ePaperDisplay;
    private SixteenSegmentDrawingHelper sixteenSegmentDrawingHelper;
    private SevenSegmentDrawingHelper sevenSegmentDrawingHelper;
    private ActiveScreenType screenType = ActiveScreenType.NO_WAYPOINT;

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

    private void clearZone(final int x0, final int y0, final int x1, final int y1) {
        this.ePaperDisplay.setColor(DisplayColor.White, DisplayColor.White);
        this.ePaperDisplay.drawRectangle(x0, y0, x1, y1, true);
        this.ePaperDisplay.setColor(DisplayColor.Black, DisplayColor.White);
    }

    public void noWayPointScreen(final BoatPosition boatPosition) {
        if (this.screenType.equals(ActiveScreenType.TO_WAYPOINT)) {
            this.noWayPointScreenInit();
            this.screenType = ActiveScreenType.NO_WAYPOINT;
        }
        this.draw16segments(new Pair<>(155, 195), 5, boatPosition.getSog());
        this.repaint();
    }

    public void noWayPointScreenInit() {
        this.clearScreen();
        this.ePaperDisplay.setEnglishFontSize(DisplayFontSize.DotMatrix_32);
        this.ePaperDisplay.displayText(155, 145, "SOG");
    }

    public void toWayPointScreen(final BoatPosition boatPosition, final Point waypoint, final Double dtw, final Double vmg) {
        if (this.screenType.equals(ActiveScreenType.NO_WAYPOINT)) {
            this.toWayPointScreenInit();
            this.screenType = ActiveScreenType.TO_WAYPOINT;
        }
        this.draw16segments(new Pair<>(40, 90), 3, boatPosition.getCog().intValue());
        this.draw16segments(new Pair<>(460, 90), 3, boatPosition.getSog());
        this.draw16segments(new Pair<>(40, 390), 3, dtw);
        this.draw16segments(new Pair<>(460, 390), 3, vmg);
        this.clearZone(0, 550, 800, 600);
        this.ePaperDisplay.displayText(5, 550, "Navegando a " + waypoint.getName());
        this.repaint();
    }

    public void toWayPointScreenInit() {
        this.clearScreen();
        this.ePaperDisplay.setEnglishFontSize(DisplayFontSize.DotMatrix_32);
        this.ePaperDisplay.displayText(40, 50, "COG");
        this.ePaperDisplay.displayText(460, 50, "SOG");
        this.ePaperDisplay.displayText(40, 350, "DTW");
        this.ePaperDisplay.displayText(460, 350, "VMG");
        this.ePaperDisplay.drawLine(0, 300, 800, 300);
        this.ePaperDisplay.drawLine(400, 0, 400, 600);
    }

    public Pair<Integer, Integer> draw16segments(final Pair<Integer, Integer> startOffset, final int scale, final String text) {
        return this.sixteenSegmentDrawingHelper.drawString(startOffset, text, scale);
    }

    public Pair<Integer, Integer> draw16segments(final Pair<Integer, Integer> startOffset, final int scale, final Integer i) {
        return this.sixteenSegmentDrawingHelper.drawInteger(startOffset, i, scale);
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

    public void drawCordinates(final Double lat, final Double lon) {
        this.clearZone(5, 10, 800, 90);
        this.ePaperDisplay.setEnglishFontSize(DisplayFontSize.DotMatrix_64);
        this.ePaperDisplay.displayText(5, 10, "POS:  " + GeoUtils.toLatitudeDMS(lat) + "      " + GeoUtils.toLongitudeDMS(lon));
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

    public void repaint() {
        if (this.ePaperDisplay != null) {
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
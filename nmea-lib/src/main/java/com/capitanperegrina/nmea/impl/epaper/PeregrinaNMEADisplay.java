package com.capitanperegrina.nmea.impl.epaper;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.simpleseven.SimpleSevenSegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.screen.OnlySpeedScreen;
import com.capitanperegrina.nmea.impl.epaper.screen.ToWaypointScreen;
import com.capitanperegrina.nmea.impl.model.impl.TrackPointDaoImpl;
import com.capitanperegrina.nmea.impl.utils.GeoUtils;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import tk.schmid.epaper.display.EPaperDisplay;
import tk.schmid.epaper.display.protocol.DisplayColor;
import tk.schmid.epaper.display.protocol.DisplayFontSize;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

public class PeregrinaNMEADisplay {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackPointDaoImpl.class);

    private enum ActiveScreenType {NO_WAYPOINT, TO_WAYPOINT}

    private static volatile PeregrinaNMEADisplay singleton;

    private EPaperDisplay ePaperDisplay;
    private SimpleSevenSegmentDrawingHelper simpleSevenSegmentDrawingHelper;
    private ActiveScreenType screenType = ActiveScreenType.NO_WAYPOINT;
    private final ToWaypointScreen toWaypointScreen = new ToWaypointScreen();
    private final OnlySpeedScreen onlySpeedScreen = new OnlySpeedScreen();

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
                this.simpleSevenSegmentDrawingHelper = new SimpleSevenSegmentDrawingHelper(this.ePaperDisplay);
                this.onlySpeedScreen.putSog(0d);
                this.toWaypointScreen.putCog(0);
                this.toWaypointScreen.putSog(0d);
                this.toWaypointScreen.putDtw(0d);
                this.toWaypointScreen.putVmc(0d);
                this.toWaypointScreen.putDestination("");
                this.ePaperDisplay.connect();
                LOGGER.info("Connected...");
                this.clearAndRepaintScreen();
            }
        }
    }

    public EPaperDisplay getePaperDisplay() {
        return this.ePaperDisplay;
    }

    @Scheduled(fixedDelay = 2000)
    public void fireRepaint() {
        if (ActiveScreenType.TO_WAYPOINT.equals(this.screenType)) {
            this.toWaypointScreen.repaint();
        } else {
            this.onlySpeedScreen.repaint();
        }
    }

    public void clearZone(final int x0, final int y0, final int x1, final int y1) {
        this.ePaperDisplay.setColor(DisplayColor.White, DisplayColor.White);
        this.ePaperDisplay.drawRectangle(x0, y0, x1, y1, true);
        this.ePaperDisplay.setColor(DisplayColor.Black, DisplayColor.White);
    }

    public void updateSpeeds(final Double speed, final Double smoothSpedd) {
        this.onlySpeedScreen.putSog(speed);
        this.toWaypointScreen.putSog(speed);
    }

    public void noWayPointScreen(final BoatPosition boatPosition) {
        if (this.screenType.equals(ActiveScreenType.TO_WAYPOINT)) {
            this.onlySpeedScreen.init();
            this.screenType = ActiveScreenType.NO_WAYPOINT;
        }
        // this.onlySpeedScreen.putSog(boatPosition.getSog());
    }

    public void toWayPointScreen(final BoatPosition boatPosition, final Point waypoint, final Double dtw, final Double vmc) {
        if (this.screenType.equals(ActiveScreenType.NO_WAYPOINT)) {
            this.screenType = ActiveScreenType.TO_WAYPOINT;
            this.toWaypointScreen.init();
        }
        this.toWaypointScreen.putCog(boatPosition.getCog().intValue());
        // Speed is set by GPS
        // this.toWaypointScreen.putSog(boatPosition.getSog());
        this.toWaypointScreen.putDtw(dtw);
        this.toWaypointScreen.putVmc(vmc);
        this.toWaypointScreen.putDestination("Navegando a " + waypoint.getName());
    }

    public Pair<Integer, Integer> draw7simpleSegments(final Pair<Integer, Integer> startOffset, final int scale,
                                                      final String text) {
        return this.simpleSevenSegmentDrawingHelper.drawString(startOffset, text, scale);
    }

    public Pair<Integer, Integer> draw7simpleSegments(final Pair<Integer, Integer> startOffset, final int scale,
                                                      final Integer i) {
        return this.simpleSevenSegmentDrawingHelper.drawInteger(startOffset, i, scale);
    }

    public void drawCordinates(final Double lat, final Double lon) {
        this.clearZone(5, 10, 800, 90);
        this.ePaperDisplay.setEnglishFontSize(DisplayFontSize.DotMatrix_64);
        this.ePaperDisplay
                .displayText(5, 10, "POS:  " + GeoUtils.toLatitudeDMS(lat) + "      " + GeoUtils.toLongitudeDMS(lon));
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

        // Wait 3 seconds.
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
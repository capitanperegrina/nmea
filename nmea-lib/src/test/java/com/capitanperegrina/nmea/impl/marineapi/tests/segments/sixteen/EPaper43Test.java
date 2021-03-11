package com.capitanperegrina.nmea.impl.marineapi.tests.segments.sixteen;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.api.model.naming.WaypointsNaming;
import com.capitanperegrina.nmea.bin.PeregrinaNMEA;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EPaper43Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeregrinaNMEA.class);

    public static void main(final String[] args) {
        try {
            LOGGER.info("Configuring...");
            final PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);
            PeregrinaNMEADisplay.getInstance().configure(params);

            final BoatPosition bp = new BoatPosition();
            bp.setLatitude(42.563214d);
            bp.setLongitude(-8.222222d);
            bp.setCog(122d);
            bp.setSog(4.17d);

            PeregrinaNMEADisplay.getInstance().clearScreen();
            PeregrinaNMEADisplay.getInstance().noWayPointScreen(bp);
            PeregrinaNMEADisplay.getInstance().toWayPointScreen(bp, WaypointsNaming.getInternalWaypoints().get(4), 2.3d, 4.08d);
            PeregrinaNMEADisplay.getInstance().updateSpeeds(3.2, 4.1);
            PeregrinaNMEADisplay.getInstance().fireRepaint();
            PeregrinaNMEADisplay.getInstance().updateSpeeds(3.4, 3.9);
            PeregrinaNMEADisplay.getInstance().toWayPointScreen(bp, WaypointsNaming.getInternalWaypoints().get(4), 2.2d, 4.18d);
            try {
                Thread.sleep(2000);
            } catch (final InterruptedException e) {
                LOGGER.info(e.getMessage());
            }
            PeregrinaNMEADisplay.getInstance().fireRepaint();

            // PeregrinaNMEADisplay.getInstance().clearScreen();
            // PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(50, 200), 3, Double.valueOf(0.12d));
            // PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(450, 200), 3, Double.valueOf(0.13d));
            // PeregrinaNMEADisplay.getInstance().drawCordinates(42.563214d, -8.222222d);
            // PeregrinaNMEADisplay.getInstance().repaint();

//            // PeregrinaNMEADisplay.getInstance().clearScreen();
//            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(155, 195), 5, Double.valueOf(3.45d));
//            PeregrinaNMEADisplay.getInstance().repaint();
//
//            // PeregrinaNMEADisplay.getInstance().clearScreen();
//            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(155, 195), 5, Double.valueOf(6.78d));
//            PeregrinaNMEADisplay.getInstance().repaint();
//
//            // PeregrinaNMEADisplay.getInstance().clearScreen();
//            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(155, 195), 5, Double.valueOf(9.00d));
//            PeregrinaNMEADisplay.getInstance().repaint();
//
//            PeregrinaNMEADisplay.getInstance().clearAndRepaintScreen();
//            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(10, 0), 3, "abcdefgh");
//            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(10, 140), 3, "ijklmnño");
//            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(10, 280), 3, "pqrstuvw");
//            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(10, 420), 3, "xyz*#\"-,.");
//            PeregrinaNMEADisplay.getInstance().repaint();
//
//            PeregrinaNMEADisplay.getInstance().clearScreen();
//            PeregrinaNMEADisplay.getInstance().draw7segments(new Pair<>(155, 195), 5, Double.valueOf(0.12d));
//            PeregrinaNMEADisplay.getInstance().repaint();
//
//            PeregrinaNMEADisplay.getInstance().clearScreen();
//            PeregrinaNMEADisplay.getInstance().draw7segments(new Pair<>(155, 195), 5, Double.valueOf(3.45d));
//            PeregrinaNMEADisplay.getInstance().repaint();
//
//            PeregrinaNMEADisplay.getInstance().clearScreen();
//            PeregrinaNMEADisplay.getInstance().draw7segments(new Pair<>(155, 195), 5, Double.valueOf(6.78d));
//            PeregrinaNMEADisplay.getInstance().repaint();
//
//            PeregrinaNMEADisplay.getInstance().clearScreen();
//            PeregrinaNMEADisplay.getInstance().draw7segments(new Pair<>(155, 195), 5, Double.valueOf(9.00d));
//            PeregrinaNMEADisplay.getInstance().repaint();

//            PeregrinaNMEADisplay.getInstance().clearAndRepaintScreen();

        } catch (final Throwable e) {
            // Catch absolutelly everything because people like me, code shit.
            LOGGER.error(e.getMessage(), e);
        } finally {
            Runtime.getRuntime().halt(0);
        }
    }

}

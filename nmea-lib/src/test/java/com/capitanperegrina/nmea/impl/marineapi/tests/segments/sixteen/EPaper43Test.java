package com.capitanperegrina.nmea.impl.marineapi.tests.segments.sixteen;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.bin.PeregrinaNMEA;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EPaper43Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeregrinaNMEA.class);

    public static void main(final String[] args) {
        try {
            LOGGER.info("Configuring...");
            final PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);
            PeregrinaNMEADisplay.getInstance().configure(params);

            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(155, 195), 5, Double.valueOf(0.12d));
            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(155, 195), 5, Double.valueOf(3.45d));
            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(155, 195), 5, Double.valueOf(6.78d));
            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(155, 195), 5, Double.valueOf(9.00d));

            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(10, 195), 3, "abcdefgh");
            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(10, 195), 3, "ijklmnño");
            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(10, 195), 3, "pqrstuvw");
            PeregrinaNMEADisplay.getInstance().draw16segments(new Pair<>(10, 195), 3, "xyz*#\"-,.");

            PeregrinaNMEADisplay.getInstance().draw7segments(new Pair<>(155, 195), 5, Double.valueOf(0.12d));
            PeregrinaNMEADisplay.getInstance().draw7segments(new Pair<>(155, 195), 5, Double.valueOf(3.45d));
            PeregrinaNMEADisplay.getInstance().draw7segments(new Pair<>(155, 195), 5, Double.valueOf(6.78d));
            PeregrinaNMEADisplay.getInstance().draw7segments(new Pair<>(155, 195), 5, Double.valueOf(9.00d));

            PeregrinaNMEADisplay.getInstance().clearAndRepaintScreen();

        } catch (final Throwable e) {
            // Catch absolutelly everything because people like me, code shit.
            LOGGER.error(e.getMessage(), e);
        } finally {
            Runtime.getRuntime().halt(0);
        }
    }

}

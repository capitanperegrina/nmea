package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
// import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import com.capitanperegrina.nmea.impl.core.PeregrinaNMEADataBuffer;
import com.capitanperegrina.nmea.impl.core.serialportreader.SerialPortReader;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;

/**
 * -i COM3 -x COM9 -o LIST
 */
public class PeregrinaNMEA {

    public static void main(String[] args) {
        PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);

        // Configuring ePaperScreen
        // PeregrinaNMEADisplay.getInstance().configure(params);

        PeregrinaNMEADataBuffer.getInstance().setWaypoint(new Point(42.394486d, -8.817429d));
        SerialPortReader spr =  new SerialPortReader(params);

        System.out.println("Starting...");
        spr.start();
        System.out.println("Started for " + params.getSeconds() + " seconds.");
        try {
            Thread.sleep(params.getSeconds()*1000);
        } catch ( InterruptedException e ) {
            System.out.println(e.getMessage());
        }
        System.out.println("Stopping...");
        spr.stop();
        System.out.println("Stopped");

        Runtime.getRuntime().halt(0);
    }
}

package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
// import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.SegmentDrawingHelper;
import com.capitanperegrina.nmea.impl.sentence.parsers.PeregrinaNMEAPendingSentences;
import com.capitanperegrina.nmea.impl.sentence.parsers.PeregrinaNMEAPendingSentencesObserver;
import com.capitanperegrina.nmea.impl.serialportreader.SerialPortReader;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;
import tk.schmid.epaper.display.EPaperDisplay;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

/**
 * -i COM3 -x COM9 -o LIST
 */
public class PeregrinaNMEA {

    public static void main(String[] args) {
        PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);

        // Configuring ePaperScreen
        // PeregrinaNMEADisplay.getInstance().configure(params);


        PeregrinaNMEAPendingSentences pendingSentences = new PeregrinaNMEAPendingSentences();
        PeregrinaNMEAPendingSentences.getObservable().addObserver(new PeregrinaNMEAPendingSentencesObserver());

        SerialPortReader spr =  new SerialPortReader(params, pendingSentences);

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

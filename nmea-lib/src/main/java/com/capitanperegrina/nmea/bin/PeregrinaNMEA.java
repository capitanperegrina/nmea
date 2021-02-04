package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.sentence.parsers.PeregrinaNMEAPendingSentences;
import com.capitanperegrina.nmea.impl.sentence.parsers.PeregrinaNMEAPendingSentencesObserver;
import com.capitanperegrina.nmea.impl.serialportreader.SerialPortReader;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;

public class PeregrinaNMEA {

    public static void main(String[] args) {
        PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);

        PeregrinaNMEAPendingSentences pendingSentences = new PeregrinaNMEAPendingSentences();
        PeregrinaNMEAPendingSentences.getObservable().addObserver(new PeregrinaNMEAPendingSentencesObserver());

        SerialPortReader spr =  new SerialPortReader(params, pendingSentences);

        int seconds = 60;
        System.out.print("Starting... ");
        spr.start();
        System.out.println("\rStarted for " + seconds + " seconds.");
        try {
            Thread.sleep(seconds*1000);
        } catch ( InterruptedException e ) {
            System.out.println(e.getMessage());
        }
        System.out.println("Stopping...");
        spr.stop();
        System.out.println("\nStopped     ");
    }
}

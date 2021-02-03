package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.sentence.parsers.PeregrinaNMEAPendingSentences;
import com.capitanperegrina.nmea.impl.sentence.parsers.PeregrinaNMEAPendingSentencesObserver;
import com.capitanperegrina.nmea.impl.serialportreader.SerialPortReader;
import com.capitanperegrina.nmea.utils.PeregrinaNMEAUtils;

import java.util.Observer;

public class PeregrinaNMEA {

    public static void main(String[] args) {
        PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);

        PeregrinaNMEAPendingSentences pendingSentences = new PeregrinaNMEAPendingSentences();
        PeregrinaNMEAPendingSentences.getObservable().addObserver(new PeregrinaNMEAPendingSentencesObserver());

        SerialPortReader spr =  new SerialPortReader(params, pendingSentences);

        spr.start();
        try {
            Thread.sleep(10000);
        } catch ( InterruptedException e ) {
            System.out.println(e.getMessage());
        }
        spr.stop();
    }
}

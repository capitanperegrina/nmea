package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.serialportreader.SerialPortReader;
import com.capitanperegrina.nmea.utils.PeregrinaNMEAUtils;

public class PeregrinaNMEA {

    public static void main(String[] args) {
        PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);
        SerialPortReader spr =  new SerialPortReader(params);
        try {
            Thread.sleep(10000);
        } catch ( InterruptedException e ) {
            System.out.println(e.getMessage());
        }
        spr.stop();
    }
}

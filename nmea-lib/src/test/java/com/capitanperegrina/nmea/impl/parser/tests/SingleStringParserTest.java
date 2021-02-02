package com.capitanperegrina.nmea.impl.parser.tests;

import com.capitanperegrina.nmea.api.model.beans.GPSInformation;
import com.capitanperegrina.nmea.api.parser.NMEAParser;
import com.capitanperegrina.nmea.impl.parser.NMEAParserImpl;
import com.capitanperegrina.nmea.utils.NMEAUtils;

public class SingleStringParserTest {

    static String GPGGA_Test = "$GPGGA,192644.000,4226.0107,N,00851.5634,W,1,03,3.9,46.2,M,52.2,M,,0000*72";

    public static void main(String [ ] args)
    {
        System.out.println("Checksum test: " + NMEAUtils.checkSumTest(GPGGA_Test));

        NMEAParser nmeaParser = new NMEAParserImpl();
        System.out.println("Checksum test: " + NMEAUtils.checkSumTest(GPGGA_Test));
        GPSInformation pos = nmeaParser.parse(GPGGA_Test);
        System.out.println(pos.toString());


    }
}

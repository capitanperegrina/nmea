package com.capitanperegrina.nmea.parser.tests;

import com.capitanperegrina.nmea.model.GPSInformation;
import com.capitanperegrina.nmea.parser.NMEAParser;
import com.capitanperegrina.nmea.parser.impl.NMEAParserImpl;
import com.capitanperegrina.nmea.utils.NMEAUtils;

public class SingleStringParserTest {

    static String GPGGA_Test = "$GPGGA,211034,4738.9577,N,12220.9329,W,1,09,1.0,10.8,M,-18.4,M,,*42";

    public static void main(String [ ] args)
    {

        System.out.println("Checksum test: " + NMEAUtils.checkSumTest("$GPGSV,3,1,11,18,87,050,48,22,56,250,49,21,55,122,49,03,40,284,47*78"));
        System.out.println("Checksum test: " + NMEAUtils.checkSumTest("$GPGSV,3,2,11,19,25,314,42,26,24,044,42,24,16,118,43,29,15,039,42*7E"));
        System.out.println("Checksum test: " + NMEAUtils.checkSumTest("$GPGSV,3,3,11,09,15,107,44,14,11,196,41,07,03,173,*4D"));
        System.out.println("Checksum test: " + NMEAUtils.checkSumTest("$GLGSV,2,1,06,65,64,037,41,66,53,269,43,88,39,200,44,74,25,051,*64"));
        System.out.println("Checksum test: " + NMEAUtils.checkSumTest("$GLGSV,2,2,06,72,16,063,35,67,01,253,*66"));

        NMEAParser nmeaParser = new NMEAParserImpl();
        System.out.println("Checksum test: " + NMEAUtils.checkSumTest(GPGGA_Test));
        GPSInformation pos = nmeaParser.parse(GPGGA_Test);
        System.out.println(pos.toString());


    }
}

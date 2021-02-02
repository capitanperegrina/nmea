package com.capitanperegrina.nmea.impl.parser.tests;

import com.capitanperegrina.nmea.api.model.beans.GPSInformation;
import com.capitanperegrina.nmea.api.parser.NMEAParser;
import com.capitanperegrina.nmea.impl.parser.NMEAParserImpl;
import com.capitanperegrina.nmea.utils.NMEAUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;

public class DeviceReaderTest {
    public static void main(String[] args) {

        NMEAParser nmeaParser = new NMEAParserImpl();

        try (BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Usuario\\Documents\\desa\\nmea\\nmea-lib\\src\\test\\resources\\nmea_data.log"));) {
            String line = null;
            while ((line = in.readLine()) != null) {
                if (NMEAUtils.checkSumTest(line)) {
                    System.out.println("Parsed sentence: " + line);
                    GPSInformation info = nmeaParser.parse(line);
//                    if ( "GPGGA".equals(info.getSentenceType())) {
//                        System.out.println("Parsed sentence: " + line);
//                        System.out.println("**** " + info.toString());
//                    }
                } else {
                    System.out.println("Checksum test failed for sentence: " + line);
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}

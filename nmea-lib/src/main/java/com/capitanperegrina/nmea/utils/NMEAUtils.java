package com.capitanperegrina.nmea.utils;

import org.apache.commons.lang3.StringUtils;

public class NMEAUtils {

    private NMEAUtils() {
        // Instance creation not allowed as it's a utils class.
    }

    public static float Latitude2Decimal(String lat, String NS) {
        float med = Float.parseFloat(lat.substring(2))/60.0f;
        med +=  Float.parseFloat(lat.substring(0, 2));
        if(NS.startsWith("S")) {
            med = -med;
        }
        return med;
    }

    public static float Longitude2Decimal(String lon, String WE) {
        float med = Float.parseFloat(lon.substring(3))/60.0f;
        med +=  Float.parseFloat(lon.substring(0, 3));
        if(WE.startsWith("W")) {
            med = -med;
        }
        return med;
    }

    public static String cleanNMEAMessage(String nmeaMessage) {
        String cleanNmeaMessage = nmeaMessage;
        if ( nmeaMessage.startsWith("$")) {
            cleanNmeaMessage = nmeaMessage.substring(1);
        }
        if ( nmeaMessage.contains("*")) {
            cleanNmeaMessage = cleanNmeaMessage.substring(0,cleanNmeaMessage.indexOf("*"));
        }
        return cleanNmeaMessage;
    }

    public static Boolean checkSumTest(String nmeaMessage) {
        String nmeaChecksum = null;
        if ( nmeaMessage.contains("*")) {
            nmeaChecksum = nmeaMessage.substring(nmeaMessage.indexOf("*")+1);
        } else {
            // No checksum found, not possible to test.
            return null;
        }
        String calculatedChecksum = checkSumCalculate(nmeaMessage);
        return nmeaChecksum.equalsIgnoreCase(calculatedChecksum);
    }

    public static String checkSumCalculate(String nmeaMessage) {
        String cleanNmeaMessage = cleanNMEAMessage(nmeaMessage);

        char checksum = 0;
        for ( char c : cleanNmeaMessage.toCharArray() ) {
            checksum = (char) (checksum ^ c);
        }
        return String.format("%02x", (int) checksum);
    }

    public static Float readFloat(String token) {
        try {
            return Float.parseFloat(token);
        } catch ( NumberFormatException e ) {
            return null;
        }
    }

    public static Integer readInteger(String token) {
        try {
            return Integer.parseInt(token);
        } catch ( NumberFormatException e ) {
            return null;
        }
    }
}

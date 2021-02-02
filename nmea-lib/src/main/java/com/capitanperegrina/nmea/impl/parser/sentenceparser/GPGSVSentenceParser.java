package com.capitanperegrina.nmea.impl.parser.sentenceparser;

import com.capitanperegrina.nmea.utils.NMEAUtils;
import org.apache.commons.lang3.StringUtils;

import com.capitanperegrina.nmea.api.model.beans.GPSInformation;
import com.capitanperegrina.nmea.api.model.beans.GPSSatellite;
import com.capitanperegrina.nmea.api.parser.sentenceparser.NMEASentenceParser;

/**
 * $GPGSV - GPS Satellites in view

 eg.
 $GPGSV,3,1,11,03,03,111,00,04,15,270,00,06,01,010,00,13,06,292,00*74
 $GPGSV,3,2,11,14,25,170,00,16,57,208,39,18,67,296,40,19,40,246,00*74
 $GPGSV,3,3,11,22,42,067,42,24,14,311,43,27,05,244,00,,,,*4D

 $GPGSV,1,1,13,02,02,213,,03,-3,000,,11,00,121,,14,13,172,05*67

 1    = Total number of messages of this type in this cycle
 2    = Message number
 3    = Total number of SVs in view
 4    = SV PRN number
 5    = Elevation in degrees, 90 maximum
 6    = Azimuth, degrees from true north, 000 to 359
 7    = SNR, 00-99 dB (null when not tracking)
 8-11 = Information about second SV, same as field 4-7
 12-15= Information about third SV, same as field 4-7
 16-19= Information about fourth SV, same as field 4-7
 */
public class GPGSVSentenceParser implements NMEASentenceParser {

    private static final int FIRST_SV_INDEX = 4;
    private static final int SECOND_SV_INDEX = 8;
    private static final int THIRD_SV_INDEX = 12;
    private static final int FOURTH_SV_INDEX = 16;

    public GPSInformation parse(final String[] tokens) {

        final GPSInformation position = new GPSInformation();
        if (tokens.length >= FIRST_SV_INDEX && StringUtils.isNotEmpty(tokens[FIRST_SV_INDEX])) {
            position.getGpsSatelliteList().add( parseFromIndex(tokens, FIRST_SV_INDEX) );
        }
        if (tokens.length >= SECOND_SV_INDEX && StringUtils.isNotEmpty(tokens[SECOND_SV_INDEX])) {
            position.getGpsSatelliteList().add( parseFromIndex(tokens, SECOND_SV_INDEX) );
        }
        if (tokens.length >= THIRD_SV_INDEX && StringUtils.isNotEmpty(tokens[THIRD_SV_INDEX])) {
            position.getGpsSatelliteList().add( parseFromIndex(tokens, THIRD_SV_INDEX) );
        }
        if (tokens.length >= FOURTH_SV_INDEX && StringUtils.isNotEmpty(tokens[FOURTH_SV_INDEX])) {
            position.getGpsSatelliteList().add( parseFromIndex(tokens, FOURTH_SV_INDEX) );
        }
        return position;
    }

    private GPSSatellite parseFromIndex(final String[] tokens, final int index) {
        GPSSatellite sat = new GPSSatellite();
        sat.setTotalMessages(NMEAUtils.readInteger(tokens[1]));
        sat.setNumber(NMEAUtils.readInteger(tokens[2]));
        sat.setSvInViewNumber(NMEAUtils.readInteger(tokens[3]));

        int i = index;
        if ( tokens.length > i ) {
            sat.setSvPrnNumber(NMEAUtils.readInteger(tokens[i]));
        } else {
            sat.setSvPrnNumber(null);
        }
        i++;

        if ( tokens.length > i ) {
            sat.setElevation(NMEAUtils.readInteger(tokens[i]));
        } else {
            sat.setElevation(null);
        }
        i++;

        if ( tokens.length > i ) {
            sat.setAzimuth(NMEAUtils.readInteger(tokens[i]));
        } else {
            sat.setAzimuth(null);
        }
        i++;

        if ( tokens.length > i ) {
            sat.setSnr(NMEAUtils.readInteger(tokens[i]));
        } else {
            sat.setSnr(null);
        }
        return sat;
    }
}

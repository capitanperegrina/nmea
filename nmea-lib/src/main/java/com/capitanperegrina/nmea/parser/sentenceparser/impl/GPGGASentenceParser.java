package com.capitanperegrina.nmea.parser.sentenceparser.impl;

import com.capitanperegrina.nmea.model.GPSInformation;
import com.capitanperegrina.nmea.parser.sentenceparser.NMEASentenceParser;
import com.capitanperegrina.nmea.utils.NMEAUtils;

/**
 * $GPGGA - Global Positioning System Fix Data
 */
public class GPGGASentenceParser implements NMEASentenceParser {

    public GPSInformation parse(String[] tokens) {
        final GPSInformation position = new GPSInformation();
        position.setTime(Float.parseFloat(tokens[1]));
        position.setLat(NMEAUtils.Latitude2Decimal(tokens[2], tokens[3]));
        position.setLon(NMEAUtils.Longitude2Decimal(tokens[4], tokens[5]));
        position.setQuality(Integer.parseInt(tokens[6]));
        position.setAltitude(Float.parseFloat(tokens[9]));
        return position;
    }
}

package com.capitanperegrina.nmea.parser.sentenceparser.impl;

import com.capitanperegrina.nmea.model.GPSInformation;
import com.capitanperegrina.nmea.parser.sentenceparser.NMEASentenceParser;
import com.capitanperegrina.nmea.utils.NMEAUtils;

/**
 * $GPRMC - Recommended minimum specific GPS/Transit data
 */
public class GPRMCSentenceParser implements NMEASentenceParser {
    public GPSInformation parse(String[] tokens) {
        final GPSInformation position = new GPSInformation();
        position.setTime(Float.parseFloat(tokens[1]));
        position.setLat(NMEAUtils.Latitude2Decimal(tokens[3], tokens[4]));
        position.setLon(NMEAUtils.Longitude2Decimal(tokens[5], tokens[6]));
        position.setVelocity(Float.parseFloat(tokens[7]));
        position.setDir(Float.parseFloat(tokens[8]));
        return position;
    }
}

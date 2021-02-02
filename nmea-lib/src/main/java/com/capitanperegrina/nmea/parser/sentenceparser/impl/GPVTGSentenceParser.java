package com.capitanperegrina.nmea.parser.sentenceparser.impl;

import com.capitanperegrina.nmea.model.GPSInformation;
import com.capitanperegrina.nmea.parser.sentenceparser.NMEASentenceParser;

/**
 * $GPVTG - Track made good and ground speed
 */
public class GPVTGSentenceParser implements NMEASentenceParser {

    public GPSInformation parse(String[] tokens) {
        final GPSInformation position = new GPSInformation();
        position.setDir(Float.parseFloat(tokens[3]));
        return position;
    }
}

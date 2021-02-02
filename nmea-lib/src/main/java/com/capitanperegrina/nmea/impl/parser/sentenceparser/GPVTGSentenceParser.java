package com.capitanperegrina.nmea.impl.parser.sentenceparser;

import com.capitanperegrina.nmea.api.model.beans.GPSInformation;
import com.capitanperegrina.nmea.api.parser.sentenceparser.NMEASentenceParser;

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

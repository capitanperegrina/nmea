package com.capitanperegrina.nmea.impl.parser.sentenceparser;

import com.capitanperegrina.nmea.api.model.beans.GPSInformation;
import com.capitanperegrina.nmea.api.parser.sentenceparser.NMEASentenceParser;

public class GPRMZSentenceParser implements NMEASentenceParser {

    public GPSInformation parse(String[] tokens) {
        final GPSInformation position = new GPSInformation();
        position.setAltitude(Float.parseFloat(tokens[1]));
        return position;
    }

}


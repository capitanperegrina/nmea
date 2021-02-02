package com.capitanperegrina.nmea.parser.sentenceparser.impl;

import com.capitanperegrina.nmea.model.GPSInformation;
import com.capitanperegrina.nmea.parser.sentenceparser.NMEASentenceParser;

public class GPRMZSentenceParser implements NMEASentenceParser {

    public GPSInformation parse(String[] tokens) {
        final GPSInformation position = new GPSInformation();
        position.setAltitude(Float.parseFloat(tokens[1]));
        return position;
    }

}


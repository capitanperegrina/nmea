package com.capitanperegrina.nmea.impl.parser.sentenceparser;

import com.capitanperegrina.nmea.api.model.beans.GPSInformation;
import com.capitanperegrina.nmea.api.parser.sentenceparser.NMEASentenceParser;
import com.capitanperegrina.nmea.utils.NMEAUtils;

public 	class GPGGLSentenceParser implements NMEASentenceParser
{
    public GPSInformation parse(String[] tokens) {
        final GPSInformation position = new GPSInformation();
        position.setLat(NMEAUtils.Latitude2Decimal(tokens[1], tokens[2]));
        position.setLon(NMEAUtils.Longitude2Decimal(tokens[3], tokens[4]));
        position.setTime(Float.parseFloat(tokens[5]));
        return position;
    }
}

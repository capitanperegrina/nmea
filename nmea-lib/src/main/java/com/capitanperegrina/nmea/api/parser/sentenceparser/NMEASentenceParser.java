package com.capitanperegrina.nmea.api.parser.sentenceparser;

import com.capitanperegrina.nmea.api.model.beans.GPSInformation;

public interface NMEASentenceParser {

	GPSInformation parse(String [] tokens);
}
package com.capitanperegrina.nmea.parser.sentenceparser;

import com.capitanperegrina.nmea.model.GPSInformation;

public interface NMEASentenceParser {

	GPSInformation parse(String [] tokens);
}
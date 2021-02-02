package com.capitanperegrina.nmea.parser;

import com.capitanperegrina.nmea.model.GPSInformation;

public interface NMEAParser {
    public GPSInformation parse(String line);
}

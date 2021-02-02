package com.capitanperegrina.nmea.api.parser;

import com.capitanperegrina.nmea.api.model.beans.GPSInformation;

public interface NMEAParser {
    public GPSInformation parse(String line);
}

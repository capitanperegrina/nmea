package com.capitanperegrina.nmea.parser.impl;

import java.util.HashMap;
import java.util.Map;

import com.capitanperegrina.nmea.model.GPSInformation;
import com.capitanperegrina.nmea.parser.NMEAParser;
import com.capitanperegrina.nmea.parser.sentenceparser.NMEASentenceParser;
import com.capitanperegrina.nmea.parser.sentenceparser.impl.GPGGASentenceParser;
import com.capitanperegrina.nmea.parser.sentenceparser.impl.GPGGLSentenceParser;
import com.capitanperegrina.nmea.parser.sentenceparser.impl.GPGSVSentenceParser;
import com.capitanperegrina.nmea.parser.sentenceparser.impl.GPRMCSentenceParser;
import com.capitanperegrina.nmea.parser.sentenceparser.impl.GPRMZSentenceParser;
import com.capitanperegrina.nmea.parser.sentenceparser.impl.GPVTGSentenceParser;

public class NMEAParserImpl implements NMEAParser {

    private final Map<String, NMEASentenceParser> sentenceParsers = new HashMap<String, NMEASentenceParser>();

    public NMEAParserImpl() {
        init();
    }

    private final void init() {
        this.sentenceParsers.put("GPGGA", new GPGGASentenceParser()); // $GPGGA - Global Positioning System Fix Data
        this.sentenceParsers.put("GPGGL", new GPGGLSentenceParser());
        this.sentenceParsers.put("GPRMC", new GPRMCSentenceParser()); // $GPRMC - Recommended minimum specific GPS/Transit data
        this.sentenceParsers.put("GPRMZ", new GPRMZSentenceParser());
        this.sentenceParsers.put("GPVTG", new GPVTGSentenceParser()); // $GPVTG - Track made good and ground speed
        this.sentenceParsers.put("GPGSV", new GPGSVSentenceParser()); // $GPGSV - GPS Satellites in view


//        $GPBOD - Bearing, origin to destination
//        $GPBWC - Bearing and distance to waypoint, great circle

//        $GPGLL - Geographic position, latitude / longitude
//        $GPGSA - GPS DOP and active satellites
//
//        $GPHDT - Heading, True
//        $GPR00 - List of waypoints in currently active route
//        $GPRMA - Recommended minimum specific Loran-C data
//        $GPRMB - Recommended minimum navigation info
//
//        $GPRTE - Routes
//        $GPTRF - Transit Fix Data
//        $GPSTN - Multiple Data ID
//        $GPVBW - Dual Ground / Water Speed
//
//        $GPWPL - Waypoint location
//        $GPXTE - Cross-track error, Measured
//        $GPZDA - Date & Time

    }

    public GPSInformation parse(String line) {
        GPSInformation position = new GPSInformation();
        if (line.startsWith("$")) {
            String nmea = line.substring(1);
            String[] tokens = nmea.split(",");
            String type = tokens[0];

            // TODO check crc
            if( this.sentenceParsers.containsKey(type) ) {
                position = sentenceParsers.get(type).parse(tokens);
            }
            position.updatefix();
        }
        return position;
    }
}



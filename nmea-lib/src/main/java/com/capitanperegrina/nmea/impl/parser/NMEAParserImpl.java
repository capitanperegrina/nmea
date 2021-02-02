package com.capitanperegrina.nmea.impl.parser;

import java.util.HashMap;
import java.util.Map;

import com.capitanperegrina.nmea.api.model.beans.GPSInformation;
import com.capitanperegrina.nmea.api.parser.NMEAParser;
import com.capitanperegrina.nmea.api.parser.sentenceparser.NMEASentenceParser;
import com.capitanperegrina.nmea.impl.parser.sentenceparser.GPGGASentenceParser;
import com.capitanperegrina.nmea.impl.parser.sentenceparser.GPGSASentenceParser;
import com.capitanperegrina.nmea.impl.parser.sentenceparser.GPGSVSentenceParser;
import com.capitanperegrina.nmea.impl.parser.sentenceparser.GPRMCSentenceParser;
import com.capitanperegrina.nmea.utils.NMEAUtils;

public class NMEAParserImpl implements NMEAParser {

    private final Map<String, NMEASentenceParser> sentenceParsers = new HashMap<String, NMEASentenceParser>();

    public NMEAParserImpl() {
        init();
    }

    private final void init() {
        this.sentenceParsers.put("GPGGA", new GPGGASentenceParser()); // $GPGGA - Global Positioning System Fix Data
        this.sentenceParsers.put("GPGSA", new GPGSASentenceParser()); // $GPGSA - GPS DOP and active satellites
        this.sentenceParsers.put("GPGSV", new GPGSVSentenceParser()); // $GPGSV - GPS Satellites in view
        this.sentenceParsers.put("GPRMC", new GPRMCSentenceParser()); // $GPRMC - Recommended minimum specific GPS/Transit data

//        this.sentenceParsers.put("GPGGL", new GPGGLSentenceParser());
//        this.sentenceParsers.put("GPRMZ", new GPRMZSentenceParser());
//        this.sentenceParsers.put("GPVTG", new GPVTGSentenceParser()); // $GPVTG - Track made good and ground speed
//        $GPBOD - Bearing, origin to destination
//        $GPBWC - Bearing and distance to waypoint, great circle
//        $GPGLL - Geographic position, latitude / longitude
//        $GPHDT - Heading, True
//        $GPR00 - List of waypoints in currently active route
//        $GPRMA - Recommended minimum specific Loran-C data
//        $GPRMB - Recommended minimum navigation info
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
            String nmea = NMEAUtils.cleanNMEAMessage(line);
            String[] tokens = nmea.split(",");
            String type = tokens[0];

            if( this.sentenceParsers.containsKey(type) ) {
                position = sentenceParsers.get(type).parse(tokens);
                position.setSentenceType(type);
            } else {
                System.out.println("*** Parser for Key " + type + " not found.");
            }
            position.updatefix();
        }
        return position;
    }
}



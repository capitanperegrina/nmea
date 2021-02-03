package com.capitanperegrina.nmea.api.parser.marineapiparsers;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.util.Date;
import net.sf.marineapi.nmea.util.Position;
import net.sf.marineapi.nmea.util.Time;

public class RMCListener extends AbstractSentenceListener<RMCSentence> {
    public void sentenceRead(RMCSentence rmc) {
        Position pos = rmc.getPosition();
        System.out.println("[" + rmc.getTime().toISO8601() + "] " + pos.getDatum().toString() + " - " + pos.toString());
    }
}

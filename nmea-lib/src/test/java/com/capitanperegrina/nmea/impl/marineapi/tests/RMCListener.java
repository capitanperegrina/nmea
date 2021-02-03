package com.capitanperegrina.nmea.impl.marineapi.tests;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.util.Date;
import net.sf.marineapi.nmea.util.Position;
import net.sf.marineapi.nmea.util.Time;

public class RMCListener extends AbstractSentenceListener<RMCSentence> {
    public void sentenceRead(RMCSentence rmc) {
        Date date = rmc.getDate();
        Time time = rmc.getTime();
        Position pos = rmc.getPosition();
        System.out.println(date.toISO8601() + " - " + time.toISO8601() + " - " + pos.toString());
    }
}

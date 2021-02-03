package com.capitanperegrina.nmea.impl.sentence.parsers.listeners;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GGASentence;
import net.sf.marineapi.nmea.util.Position;

public class GGAListener extends AbstractSentenceListener<GGASentence> {
    public void sentenceRead(GGASentence gga) {
        Position pos = gga.getPosition();
        System.out.println("[" + gga.getTime().toISO8601() + "] " + pos.getDatum().toString() + " - " + pos.toString());
    }
}

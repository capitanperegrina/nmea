package com.capitanperegrina.nmea.api.parser.marineapiparsers;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GLLSentence;
import net.sf.marineapi.nmea.util.Position;

public class GLLListener extends AbstractSentenceListener<GLLSentence> {
    public void sentenceRead(GLLSentence gll) {
        Position pos = gll.getPosition();
        System.out.println("[" + gll.getTime().toISO8601() + "] " + pos.getDatum().toString() + " - " + pos.toString());
    }
}

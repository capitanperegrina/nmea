package com.capitanperegrina.nmea.impl.marineapi.tests;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GGASentence;
import net.sf.marineapi.nmea.util.Position;

public class GGAListener extends AbstractSentenceListener<GGASentence> {
    public void sentenceRead(GGASentence gga) {
        Position pos = gga.getPosition();
        System.out.println(pos.getDatum().toString() + " - " + pos.toString());
    }
}

package com.capitanperegrina.nmea.impl.marineapi.tests.listeners;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GGASentence;
import net.sf.marineapi.nmea.sentence.GSVSentence;
import net.sf.marineapi.nmea.util.Position;

public class GSVListener extends AbstractSentenceListener<GSVSentence> {
    public void sentenceRead(GSVSentence gsv) {
        System.out.println(gsv.toString());
    }
}

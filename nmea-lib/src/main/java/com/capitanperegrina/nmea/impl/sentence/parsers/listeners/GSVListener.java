package com.capitanperegrina.nmea.impl.sentence.parsers.listeners;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GSVSentence;

public class GSVListener extends AbstractSentenceListener<GSVSentence> {
    public void sentenceRead(GSVSentence gsv) {
        System.out.println(gsv.toString());
    }
}

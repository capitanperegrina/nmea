package com.capitanperegrina.nmea.api.parser.marineapiparsers;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GSVSentence;

public class GSVListener extends AbstractSentenceListener<GSVSentence> {
    public void sentenceRead(GSVSentence gsv) {
        System.out.println(gsv.toString());
    }
}

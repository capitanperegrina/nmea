package com.capitanperegrina.nmea.api.parser.marineapiparsers;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GSASentence;

public class GSAListener  extends AbstractSentenceListener<GSASentence> {
    public void sentenceRead(GSASentence gsa) {
        System.out.println(gsa.toString());
    }
}

package com.capitanperegrina.nmea.impl.sentence.parsers.listeners;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GSASentence;

public class GSAListener  extends AbstractSentenceListener<GSASentence> {
    public void sentenceRead(GSASentence gsa) {
        System.out.println(gsa.toString());
    }
}

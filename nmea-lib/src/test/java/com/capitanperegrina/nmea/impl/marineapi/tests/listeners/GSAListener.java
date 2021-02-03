package com.capitanperegrina.nmea.impl.marineapi.tests.listeners;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GGASentence;
import net.sf.marineapi.nmea.sentence.GSASentence;
import net.sf.marineapi.nmea.util.Position;

public class GSAListener  extends AbstractSentenceListener<GSASentence> {
    public void sentenceRead(GSASentence gsa) {
        System.out.println(gsa.toString());
    }
}

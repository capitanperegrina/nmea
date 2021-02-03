package com.capitanperegrina.nmea.impl.sentence.parsers.listeners;

import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.VTGSentence;

public class VTGListener  extends AbstractSentenceListener<VTGSentence> {
    public void sentenceRead(VTGSentence vtg) {
        System.out.printf("SOG: %f Knots / %f Km/h\n",
                vtg.getSpeedKmh(),
                vtg.getSpeedKnots() );
    }
}

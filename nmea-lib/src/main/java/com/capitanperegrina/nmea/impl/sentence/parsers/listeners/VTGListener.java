package com.capitanperegrina.nmea.impl.sentence.parsers.listeners;

import com.capitanperegrina.nmea.impl.utils.SentenceToBeanUtils;
import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.VTGSentence;

public class VTGListener  extends AbstractSentenceListener<VTGSentence> {
    public void sentenceRead(VTGSentence vtg) {
        System.out.println(SentenceToBeanUtils.toBean(vtg).toString());
    }
}

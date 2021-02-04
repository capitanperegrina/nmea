package com.capitanperegrina.nmea.impl.sentence.parsers.listeners;

import com.capitanperegrina.nmea.impl.utils.SentenceToBeanUtils;
import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GSASentence;
import net.sf.marineapi.nmea.sentence.GSVSentence;

public class GSVListener extends AbstractSentenceListener<GSVSentence> {
    public void sentenceRead(GSVSentence gsv) {
        System.out.println(SentenceToBeanUtils.toBean(gsv));
    }
}

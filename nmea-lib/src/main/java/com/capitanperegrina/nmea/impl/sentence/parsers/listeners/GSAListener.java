package com.capitanperegrina.nmea.impl.sentence.parsers.listeners;

import com.capitanperegrina.nmea.impl.utils.SentenceToBeanUtils;
import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.GSASentence;

public class GSAListener extends AbstractSentenceListener<GSASentence> {
    public void sentenceRead(GSASentence gsa) {
        System.out.println(SentenceToBeanUtils.toBean(gsa).toString());
    }
}

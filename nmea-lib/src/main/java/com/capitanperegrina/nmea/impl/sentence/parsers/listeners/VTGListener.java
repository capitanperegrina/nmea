package com.capitanperegrina.nmea.impl.sentence.parsers.listeners;

import com.capitanperegrina.nmea.api.model.beans.nmea.VTGBean;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.utils.SentenceToBeanUtils;
import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.VTGSentence;

public class VTGListener  extends AbstractSentenceListener<VTGSentence> {
    public void sentenceRead(VTGSentence vtg) {
        VTGBean vtgBean = SentenceToBeanUtils.toBean(vtg);
        System.out.println(vtgBean.toString());
        // PeregrinaNMEADisplay.getInstance().draw(10,10, vtgBean.getSpeedKnots());
    }
}

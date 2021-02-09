package com.capitanperegrina.nmea.impl.sentence.parsers.listeners;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import com.capitanperegrina.nmea.impl.core.PeregrinaNMEADataBuffer;
import com.capitanperegrina.nmea.impl.sentence.parsers.PeregrinaNMEAPendingSentences;
import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.util.Date;
import net.sf.marineapi.nmea.util.Position;
import net.sf.marineapi.nmea.util.Time;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class RMCListener extends AbstractSentenceListener<RMCSentence> {
    public void sentenceRead(RMCSentence rmc) {
        Position pos = rmc.getPosition();
        BoatPosition p = new BoatPosition(pos.getLatitude(), pos.getLongitude(), rmc.getDate().toISO8601(rmc.getTime()));
        PeregrinaNMEADataBuffer.getInstance().addElement(p);
        // System.out.println(p.toString());
    }
}

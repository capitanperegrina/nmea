package com.capitanperegrina.nmea.impl.core.parsers;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.nmea.VTGBean;
import com.capitanperegrina.nmea.impl.core.PeregrinaNMEADataBuffer;
import com.capitanperegrina.nmea.impl.utils.SentenceToBeanUtils;
import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.sentence.VTGSentence;
import net.sf.marineapi.nmea.util.Position;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class SentenceParserHelper {

    private final SentenceFactory sf = SentenceFactory.getInstance();

    public void parse(String sentence) {
        if ( sentence.startsWith("$GPVTG") ) {
            parse((VTGSentence) sf.createParser(sentence));
        } else if ( sentence.startsWith("$GPRMC")) {
            parse((RMCSentence) sf.createParser(sentence));
        }
    }

    private void parse(VTGSentence vtg) {
        VTGBean vtgBean = SentenceToBeanUtils.toBean(vtg);
        PeregrinaNMEADataBuffer.getInstance().addSpeed(vtg.getSpeedKnots());
    }

    private void parse(RMCSentence rmc) {
        Position pos = rmc.getPosition();
        DateTimeFormatter jodaTimeParser = ISODateTimeFormat.dateTimeNoMillis();
        BoatPosition p = new BoatPosition(pos.getLatitude(), pos.getLongitude(), jodaTimeParser.parseDateTime(rmc.getDate().toISO8601(rmc.getTime())).toDate());
        PeregrinaNMEADataBuffer.getInstance().addPostion(p);
    }
}

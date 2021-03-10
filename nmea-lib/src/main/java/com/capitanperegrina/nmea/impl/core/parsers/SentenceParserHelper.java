package com.capitanperegrina.nmea.impl.core.parsers;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.impl.core.PeregrinaNMEADataBuffer;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.util.Position;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SentenceParserHelper {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SentenceParserHelper.class);

    private static final int COMAND_START_POSITION_IN_SENTENCE = 3;
    private static final int COMAND_END_POSITION_IN_SENTENCE = 6;

    private static final String VTG = "VTG";
    private static final String RMC = "RMC";

    private final Map<String, Boolean> dataAvailability = new HashMap<>();

    private final SentenceFactory sf = SentenceFactory.getInstance();

    public SentenceParserHelper() {
        this.dataAvailable(VTG);
        this.dataAvailable(RMC);
    }

    public void parse(final String sentence) {
        LOGGER.debug("Parsing {}", sentence);
        if (sentence.length() >= COMAND_END_POSITION_IN_SENTENCE) {
            final String code = sentence.substring(COMAND_START_POSITION_IN_SENTENCE, COMAND_END_POSITION_IN_SENTENCE);
//            if (code.equals(VTG)) {
//                this.parse((VTGSentence) this.sf.createParser(sentence));
//            }
            if (code.equals(RMC)) {
                this.parse((RMCSentence) this.sf.createParser(sentence));
            }
        } else {
            LOGGER.warn("Sentence {} not parseable.", sentence);
        }

    }

//    private void parse(final VTGSentence vtg) {
//        LOGGER.trace("Parsing VTG" + vtg.toString());
//        try {
//            final VTGBean vtgBean = SentenceToBeanUtils.toBean(vtg);
//            PeregrinaNMEADataBuffer.getInstance().addSpeed(vtg.getSpeedKnots());
//            this.dataAvailable(VTG);
//        } catch (final DataNotAvailableException e) {
//            if (this.shouldShowErrorMessage(VTG)) {
//                LOGGER.error("{} Data not available", VTG);
//            }
//            this.dataUnvailable(VTG);
//        }
//    }

    private void parse(final RMCSentence rmc) {
        try {
            LOGGER.trace("Parsing RMC" + rmc.toString());
            final Position pos = rmc.getPosition();
            final DateTimeFormatter jodaTimeParser = ISODateTimeFormat.dateTimeNoMillis();
            final BoatPosition p = new BoatPosition(pos.getLatitude(), pos.getLongitude(), jodaTimeParser.parseDateTime(rmc.getDate().toISO8601(rmc.getTime())).toDate());
            PeregrinaNMEADataBuffer.getInstance().addPostion(p);
            this.dataAvailable(RMC);
        } catch (final DataNotAvailableException e) {
            if (this.shouldShowErrorMessage(RMC)) {
                LOGGER.error("{} Data not available", RMC);
            }
            this.dataUnvailable(RMC);
        }
    }

    private void dataAvailable(final String code) {
        this.dataAvailability.put(code, Boolean.TRUE);
    }

    private void dataUnvailable(final String code) {
        this.dataAvailability.put(code, Boolean.FALSE);
    }

    private Boolean shouldShowErrorMessage(final String code) {
        return this.dataAvailability.get(code);
    }
}
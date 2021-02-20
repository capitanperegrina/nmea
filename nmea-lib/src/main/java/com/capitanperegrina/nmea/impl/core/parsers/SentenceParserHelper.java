package com.capitanperegrina.nmea.impl.core.parsers;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.nmea.VTGBean;
import com.capitanperegrina.nmea.impl.core.PeregrinaNMEADataBuffer;
import com.capitanperegrina.nmea.impl.utils.SentenceToBeanUtils;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.sentence.VTGSentence;
import net.sf.marineapi.nmea.util.Position;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SentenceParserHelper {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SentenceParserHelper.class);

    private static final int COMAND_START_POSITION_IN_SENTENCE = 3;
    private static final int COMAND_END_POSITION_IN_SENTENCE = 6;

    private static final String VTG = "VTG";
    private static final String RMC = "RMC";

    private Map<String,Boolean> dataAvailability = new HashMap<>();

    private final SentenceFactory sf = SentenceFactory.getInstance();

    public SentenceParserHelper() {
        dataAvailable(VTG);
        dataAvailable(RMC);
    }

    public void parse(String sentence) {
        LOGGER.debug("Parsing {}", sentence);
        if (sentence.length()>=COMAND_END_POSITION_IN_SENTENCE) {
            String code = sentence.substring(COMAND_START_POSITION_IN_SENTENCE, COMAND_END_POSITION_IN_SENTENCE);
            if (code.equals(VTG)) {
                parse((VTGSentence) sf.createParser(sentence));
            }
            if (code.equals(RMC)) {
                parse((RMCSentence) sf.createParser(sentence));
            }
        } else {
            LOGGER.warn("Sentence {} not parseable.", sentence);
        }

    }

    private void parse(VTGSentence vtg) {
        LOGGER.trace("Parsing VTG" + vtg.toString());
        try {
            VTGBean vtgBean = SentenceToBeanUtils.toBean(vtg);
            PeregrinaNMEADataBuffer.getInstance().addSpeed(vtg.getSpeedKnots());
            dataAvailable(VTG);
        } catch (DataNotAvailableException e) {
            if ( shouldShowErrorMessage(VTG)) {
                LOGGER.error("{} Data not available", VTG);
            }
            dataUnvailable(VTG);
        }
    }

    private void parse(RMCSentence rmc) {
        try {
            LOGGER.trace("Parsing RMC" + rmc.toString());
            Position pos = rmc.getPosition();
            DateTimeFormatter jodaTimeParser = ISODateTimeFormat.dateTimeNoMillis();
            BoatPosition p = new BoatPosition(pos.getLatitude(), pos.getLongitude(), jodaTimeParser.parseDateTime(rmc.getDate().toISO8601(rmc.getTime())).toDate());
            PeregrinaNMEADataBuffer.getInstance().addPostion(p);
            dataAvailable(RMC);
        } catch (DataNotAvailableException e) {
            if ( shouldShowErrorMessage(RMC)) {
                LOGGER.error("{} Data not available", RMC);
            }
            dataUnvailable(RMC);
        }
    }

    private void dataAvailable(String code) {
        this.dataAvailability.put(code,Boolean.TRUE);
    }

    private void dataUnvailable(String code) {
        this.dataAvailability.put(code,Boolean.FALSE);
    }

    private Boolean shouldShowErrorMessage(String code) {
        return this.dataAvailability.get(code);
    }
}
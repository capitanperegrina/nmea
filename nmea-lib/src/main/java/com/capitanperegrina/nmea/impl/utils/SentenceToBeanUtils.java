package com.capitanperegrina.nmea.impl.utils;

import com.capitanperegrina.nmea.api.model.beans.nmea.GSABean;
import com.capitanperegrina.nmea.api.model.beans.nmea.GSVBean;
import com.capitanperegrina.nmea.api.model.beans.nmea.VTGBean;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.sentence.GSASentence;
import net.sf.marineapi.nmea.sentence.GSVSentence;
import net.sf.marineapi.nmea.sentence.VTGSentence;

public class SentenceToBeanUtils {

    private SentenceToBeanUtils() {
        // Instance creation not allowed as it's a utils class.
    }

    public static GSABean toBean(GSASentence sentence) {
        GSABean bean = new GSABean();
        bean.setFixStatus(sentence.getFixStatus());
        try {
            bean.setHorizontalDOP(sentence.getHorizontalDOP());
        } catch ( DataNotAvailableException e ) {
            bean.setHorizontalDOP(null);
        }
        bean.setMode(sentence.getMode());
        try {
            bean.setPositionDOP(sentence.getPositionDOP());
        } catch ( DataNotAvailableException e ) {
            bean.setPositionDOP(null);
        }
        bean.setSatelliteIds(sentence.getSatelliteIds());
        try {
            bean.setVerticalDOP( sentence.getVerticalDOP() );
        } catch ( DataNotAvailableException e ) {
            bean.setVerticalDOP(null);
        }
        return bean;
    }

    public static GSVBean toBean(GSVSentence sentence) {
        GSVBean bean = new GSVBean();
        try {
            bean.setSatelliteCount(sentence.getSatelliteCount());
        } catch (DataNotAvailableException e) {
            bean.setSatelliteCount(null);
        }
        bean.setSatelliteInfo(sentence.getSatelliteInfo());
        try {
            bean.setSentenceCount(sentence.getSentenceCount());
        } catch (DataNotAvailableException e) {
            bean.setSentenceCount(null);
        }
        try {
            bean.setSentenceIndex(sentence.getSentenceIndex());
        } catch (DataNotAvailableException e) {
            bean.setSentenceIndex(null);
        }
        try {
            bean.setFirst(sentence.isFirst());
        } catch (DataNotAvailableException e) {
            bean.setFirst(null);
        }
        try {
            bean.setLast(sentence.isLast());
        } catch (DataNotAvailableException e) {
            bean.setLast(null);
        }
        return bean;
    }

    public static VTGBean toBean(VTGSentence sentence) {
        VTGBean bean = new VTGBean();

        try {
            bean.setMagneticCourse(sentence.getMagneticCourse());
        } catch (DataNotAvailableException e) {
            bean.setMagneticCourse(null);
        }
        bean.setMode(sentence.getMode());
        try {
            bean.setSpeedKmh(sentence.getSpeedKmh());
        } catch (DataNotAvailableException e) {
            bean.setSpeedKmh(null);
        }
        try {
            bean.setSpeedKnots(sentence.getSpeedKnots());
        } catch (DataNotAvailableException e) {
            bean.setSpeedKnots(null);
        }
        try {
            bean.setTrueCourse(sentence.getTrueCourse());
        } catch (DataNotAvailableException e) {
            bean.setTrueCourse(null);
        }
        return bean;
    }
}

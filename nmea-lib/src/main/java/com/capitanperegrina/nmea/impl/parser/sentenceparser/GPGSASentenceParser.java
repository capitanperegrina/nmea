package com.capitanperegrina.nmea.impl.parser.sentenceparser;

import com.capitanperegrina.nmea.api.model.beans.GPSDOP;
import com.capitanperegrina.nmea.api.model.beans.GPSInformation;
import com.capitanperegrina.nmea.api.model.beans.GPSSatellite;
import com.capitanperegrina.nmea.api.model.enums.GPSOperationFixMode;
import com.capitanperegrina.nmea.api.model.enums.GPSOperationMode;
import com.capitanperegrina.nmea.api.parser.sentenceparser.NMEASentenceParser;
import org.apache.commons.lang3.StringUtils;

public class GPGSASentenceParser implements NMEASentenceParser {

    @Override
    public GPSInformation parse(String[] tokens) {

        final GPSInformation position = new GPSInformation();
        GPSDOP dop = new GPSDOP();
        if ( GPSOperationMode.AUTOMATIC.getValue().equals(tokens[1]) ) {
            dop.setOperationMode(GPSOperationMode.AUTOMATIC);
        } else if ( GPSOperationMode.MANUAL.getValue().equals(tokens[1]) ) {
            dop.setOperationMode(GPSOperationMode.MANUAL);
        } else {
            dop.setOperationMode(null);
        }

        if ( GPSOperationFixMode.NOT_AVAILABLE.getValue() == (Integer.parseInt(tokens[2])) ) {
            dop.setOperationFixMode(GPSOperationFixMode.NOT_AVAILABLE);
        } else if ( GPSOperationFixMode.FIX_2D.getValue() == (Integer.parseInt(tokens[2])) ) {
            dop.setOperationFixMode(GPSOperationFixMode.FIX_2D);
        } else if ( GPSOperationFixMode.FIX_3D.getValue() == (Integer.parseInt(tokens[2])) ) {
            dop.setOperationFixMode(GPSOperationFixMode.FIX_3D);
        } else {
            dop.setOperationFixMode(null);
        }

        for ( int i = 3; i <= 14; i++ ) {
            if ( StringUtils.isNotEmpty(tokens[i]) && StringUtils.isNumeric(tokens[i])) {
                dop.getSatelliteIds().add(Integer.parseInt(tokens[i]));
            }
        }
        dop.setPdop(Float.parseFloat(tokens[15]));
        dop.setHdop(Float.parseFloat(tokens[16]));
        dop.setVdop(Float.parseFloat(tokens[17]));
        position.setGpsDop(dop);
        return position;
    }
}

package com.capitanperegrina.nmea.impl.core.serialportreader.listener;

import com.capitanperegrina.nmea.api.model.PeregrinaNMEAOperations;
import com.capitanperegrina.nmea.impl.core.parsers.SentenceParserHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.SegmentDrawingHelper;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class SerialPortReaderListener implements SerialPortEventListener {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SerialPortReaderListener.class);

    private static String CR = "\n";
    private static String LF = "\r";

    private final SentenceParserHelper sp = new SentenceParserHelper();
    private final SerialPort serialPort;
    private final StringBuilder buffer;
    private final String operation;

    public SerialPortReaderListener(SerialPort serialPort, String operation) {
        this.serialPort = serialPort;
        this.buffer = new StringBuilder();
        this.operation = operation;
    }

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) { //If data is available
            try {
                String caracter = this.serialPort.readString(1);
                if ( CR.equals(caracter) || LF.equals(caracter) ) {
                    if (buffer.length() > 0) {
                        String nmea = buffer.toString();
                        if (StringUtils.isNotEmpty(nmea)) {
                            if (PeregrinaNMEAOperations.LIST.toString().equals(this.operation)) {
                                System.out.println(nmea);
                            } else if (PeregrinaNMEAOperations.PARSE.toString().equals(this.operation)) {
                                this.sp.parse(nmea);
                            }
                        }
                    }
                    buffer.setLength(0);
                } else {
                    this.buffer.append(caracter);
                }
            } catch (SerialPortException ex) {
                ex.printStackTrace();
            }
        } else if (event.isCTS()) {//If CTS line has changed state
            if (event.getEventValue() == 1) {//If line is ON
                LOGGER.info("CTS - ON");
            } else {
                LOGGER.info("CTS - OFF");
            }
        } else if (event.isDSR()) {///If DSR line has changed state
            if (event.getEventValue() == 1) {//If line is ON
                LOGGER.info("DSR - ON");
            } else {
                LOGGER.info("DSR - ON");
            }
        }
    }

    public StringBuilder getBuffer() {
        return buffer;
    }
}



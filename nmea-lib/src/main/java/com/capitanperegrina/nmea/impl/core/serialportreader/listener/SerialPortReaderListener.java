package com.capitanperegrina.nmea.impl.core.serialportreader.listener;

import com.capitanperegrina.nmea.impl.core.parsers.SentenceParserHelper;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.apache.commons.lang3.StringUtils;

public class SerialPortReaderListener implements SerialPortEventListener {

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
                            if ( operation.equals("LIST")) {
                                System.out.println(nmea);
                            } else if ( operation.equals("PARSE")) {
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
                System.out.println("CTS - ON");
            } else {
                System.out.println("CTS - OFF");
            }
        } else if (event.isDSR()) {///If DSR line has changed state
            if (event.getEventValue() == 1) {//If line is ON
                System.out.println("DSR - ON");
            } else {
                System.out.println("DSR - OFF");
            }
        }
    }

    public StringBuilder getBuffer() {
        return buffer;
    }
}


package com.capitanperegrina.nmea.impl.serialportreader;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.sentence.parsers.PeregrinaNMEAPendingSentences;
import com.capitanperegrina.nmea.impl.serialportreader.listener.SerialPortReaderListener;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialPortReader {

    private PeregrinaNMEAExcutionParameters params;
    private PeregrinaNMEAPendingSentences pendingSentences;
    private SerialPort serialPort;

    public SerialPortReader( PeregrinaNMEAExcutionParameters params, PeregrinaNMEAPendingSentences pendingSentences ) {
        this.params = params;
        this.pendingSentences = pendingSentences;
    }

    public void start() {
        this.serialPort = new SerialPort(params.getSerialPortName());
        try {
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;
            this.serialPort.openPort();
            this.serialPort.setParams(params.getBaudRate(), params.getDataBits(), params.getStopBits(), params.getParity());
            this.serialPort.setEventsMask(mask);
            this.serialPort.addEventListener(new SerialPortReaderListener(serialPort, params.getOperation(), pendingSentences));
        } catch ( SerialPortException ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        try {
            this.serialPort.closePort();
        } catch ( SerialPortException ex) {
            ex.printStackTrace();
        }
    }
}

package com.capitanperegrina.nmea.impl.core.serialportreader;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.core.serialportreader.listener.SerialPortReaderListener;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialPortReader {

    private PeregrinaNMEAExcutionParameters params;
    private SerialPort serialPort;

    public SerialPortReader(PeregrinaNMEAExcutionParameters params) {
        this.params = params;
        this.serialPort = new SerialPort(params.getSerialPortName());
    }

    public void start() {
        try {
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;
            this.serialPort.openPort();
            this.serialPort.setParams(params.getBaudRate(), params.getDataBits(), params.getStopBits(), params.getParity());
            this.serialPort.setEventsMask(mask);
            this.serialPort.addEventListener(new SerialPortReaderListener(serialPort, params.getOperation()));
        } catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        try {
            this.serialPort.closePort();
        } catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }
}
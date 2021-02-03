package com.capitanperegrina.nmea.impl.serialportreader;

import com.capitanperegrina.nmea.impl.serialportreader.listener.SerialPortReaderListener;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialPortReader {

    private SerialPort serialPort;

    public SerialPortReader( String serialPortName, int baudRate, int dataBits, int stopBits, int parity ) {
        this.serialPort = new SerialPort(serialPortName);
        try {
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;
            this.serialPort.openPort();
            this.serialPort.setParams(baudRate, dataBits, stopBits, parity);
            this.serialPort.setEventsMask(mask);
            this.serialPort.addEventListener(new SerialPortReaderListener(serialPort));
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

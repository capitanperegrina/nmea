package com.capitanperegrina.nmea.impl.serialportreader.listener;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialPortReaderListener implements SerialPortEventListener {

    private static byte CR = 13;
    private static byte LF = 10;

    private final SerialPort serialPort;
    private final StringBuilder buffer;

    public SerialPortReaderListener(SerialPort serialPort) {
        this.serialPort = serialPort;
        this.buffer = new StringBuilder();
    }

    /**
     * **********************************************
     * \n: false
     * \r: false
     * buffer contains \n: false
     * buffer contains \r: false
     * **********************************************
     * \n: false
     * \r: false
     * buffer contains \n: false
     * buffer contains \r: false
     * **********************************************
     * \n: false
     * \r: true
     * buffer contains \n: false
     * buffer contains \r: true
     * **********************************************
     * \n: true
     * \r: false
     * buffer contains \n: true
     * buffer contains \r: true
     * **********************************************
     */
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) { //If data is available
            try {
                String caracter = this.serialPort.readString(1);
                this.buffer.append(caracter);
                System.out.println("**********************************************");
                System.out.println("\\n: " + "\n".equals(caracter));
                System.out.println("\\r: " + "\r".equals(caracter));
                System.out.println("buffer contains \\n: " + this.buffer.toString().contains("\n"));
                System.out.println("buffer contains \\r: " + this.buffer.toString().contains("\r"));
                // System.out.print(caracter);
                // System.out.print("***" + this.buffer.length() +  "**************************\n" + this.buffer.toString());
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



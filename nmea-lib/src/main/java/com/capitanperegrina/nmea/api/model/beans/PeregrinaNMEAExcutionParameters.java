package com.capitanperegrina.nmea.api.model.beans;

public class PeregrinaNMEAExcutionParameters {

    private final String serialPortName;
    private final Integer baudRate;
    private final Integer dataBits;
    private final Integer stopBits;
    private final Integer parity;
    private final String operation;

    public PeregrinaNMEAExcutionParameters(String serialPortName, Integer baudRate, Integer dataBits,
            Integer stopBits, Integer parity, String operation) {
        this.serialPortName = serialPortName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        this.operation = operation;
    }

    public String getSerialPortName() {
        return serialPortName;
    }

    public Integer getBaudRate() {
        return baudRate;
    }

    public Integer getDataBits() {
        return dataBits;
    }

    public Integer getStopBits() {
        return stopBits;
    }

    public Integer getParity() {
        return parity;
    }

    public String getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return "PeregrinaNMEAExcutionParameters{" +
                "serialPortName='" + serialPortName + '\'' +
                ", baudRate=" + baudRate +
                ", dataBits=" + dataBits +
                ", stopBits=" + stopBits +
                ", parity=" + parity +
                ", operation='" + operation + '\'' +
                '}';
    }
}

package com.capitanperegrina.nmea.api.model.beans;

import com.capitanperegrina.nmea.api.model.enums.GPSOperationFixMode;
import com.capitanperegrina.nmea.api.model.enums.GPSOperationMode;

import java.util.ArrayList;
import java.util.List;

public class GPSDOP {

    private GPSOperationMode operationMode;
    private GPSOperationFixMode operationFixMode;
    private final List<Integer> satelliteIds = new ArrayList<>();
    private float pdop;
    private float hdop;
    private float vdop;

    public GPSOperationMode getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(GPSOperationMode operationMode) {
        this.operationMode = operationMode;
    }

    public GPSOperationFixMode getOperationFixMode() {
        return operationFixMode;
    }

    public void setOperationFixMode(GPSOperationFixMode operationFixMode) {
        this.operationFixMode = operationFixMode;
    }

    public List<Integer> getSatelliteIds() {
        return satelliteIds;
    }

    public float getPdop() {
        return pdop;
    }

    public void setPdop(float pdop) {
        this.pdop = pdop;
    }

    public float getHdop() {
        return hdop;
    }

    public void setHdop(float hdop) {
        this.hdop = hdop;
    }

    public float getVdop() {
        return vdop;
    }

    public void setVdop(float vdop) {
        this.vdop = vdop;
    }

    @Override
    public String toString() {
        return "GPSDOP{" +
                "operationMode=" + operationMode +
                ", operationFixMode=" + operationFixMode +
                ", satelliteIds=" + satelliteIds +
                ", pdop=" + pdop +
                ", hdop=" + hdop +
                ", vdop=" + vdop +
                '}';
    }
}

package com.capitanperegrina.nmea.api.model.beans.nmea;

import net.sf.marineapi.nmea.util.FaaMode;
import net.sf.marineapi.nmea.util.GpsFixStatus;

import java.util.Arrays;

public class GSABean {

    private GpsFixStatus fixStatus;
    private Double horizontalDOP;
    private FaaMode mode;
    private Double positionDOP;
    private String[] satelliteIds;
    private Double verticalDOP;

    public GpsFixStatus getFixStatus() {
        return fixStatus;
    }

    public void setFixStatus(GpsFixStatus fixStatus) {
        this.fixStatus = fixStatus;
    }

    public Double getHorizontalDOP() {
        return horizontalDOP;
    }

    public void setHorizontalDOP(Double horizontalDOP) {
        this.horizontalDOP = horizontalDOP;
    }

    public FaaMode getMode() {
        return mode;
    }

    public void setMode(FaaMode mode) {
        this.mode = mode;
    }

    public Double getPositionDOP() {
        return positionDOP;
    }

    public void setPositionDOP(Double positionDOP) {
        this.positionDOP = positionDOP;
    }

    public String[] getSatelliteIds() {
        return satelliteIds;
    }

    public void setSatelliteIds(String[] satelliteIds) {
        this.satelliteIds = satelliteIds;
    }

    public Double getVerticalDOP() {
        return verticalDOP;
    }

    public void setVerticalDOP(Double verticalDOP) {
        this.verticalDOP = verticalDOP;
    }

    @Override
    public String toString() {
        return "GSABean{" +
                "fixStatus=" + fixStatus +
                ", horizontalDOP=" + horizontalDOP +
                ", mode=" + mode +
                ", positionDOP=" + positionDOP +
                ", satelliteIds=" + Arrays.toString(satelliteIds) +
                ", verticalDOP=" + verticalDOP +
                '}';
    }
}

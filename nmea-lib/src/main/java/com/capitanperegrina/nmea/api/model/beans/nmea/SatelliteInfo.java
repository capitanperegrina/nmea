package com.capitanperegrina.nmea.api.model.beans.nmea;

public class SatelliteInfo {
    private String id;
    private int elevation;
    private int azimuth;
    private int noise;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public int getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(int azimuth) {
        this.azimuth = azimuth;
    }

    public int getNoise() {
        return noise;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    @Override
    public String toString() {
        return "SatelliteInfo{" +
                "id='" + id + '\'' +
                ", elevation=" + elevation +
                ", azimuth=" + azimuth +
                ", noise=" + noise +
                '}';
    }
}

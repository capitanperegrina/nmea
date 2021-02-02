package com.capitanperegrina.nmea.model;

import java.util.ArrayList;
import java.util.List;

public class GPSInformation {
    
    private float time = 0.0f;
    private float lat = 0.0f;
    private float lon = 0.0f;
    private boolean fixed = false;
    private int quality = 0;
    private float dir = 0.0f;
    private float altitude = 0.0f;
    private float velocity = 0.0f;
    private final List<GPSSatellite> gpsSatelliteList = new ArrayList<>();
    
    public void updatefix() {
        fixed = quality > 0;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public float getDir() {
        return dir;
    }

    public void setDir(float dir) {
        this.dir = dir;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public List<GPSSatellite> getGpsSatelliteList() {
        return gpsSatelliteList;
    }

    @Override
    public String toString() {
        return "GPSPosition{" +
                "time=" + time +
                ", lat=" + lat +
                ", lon=" + lon +
                ", fixed=" + fixed +
                ", quality=" + quality +
                ", dir=" + dir +
                ", altitude=" + altitude +
                ", velocity=" + velocity +
                ", gpsSatelliteList=" + gpsSatelliteList +
                '}';
    }
}
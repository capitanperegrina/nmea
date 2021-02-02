package com.capitanperegrina.nmea.api.model.beans;

import java.util.ArrayList;
import java.util.List;

public class GPSInformation {

    private String sentenceType = null;
    private Float time = 0.0f;
    private Float lat = 0.0f;
    private Float lon = 0.0f;
    private Boolean fixed = false;
    private Integer quality = 0;
    private Float dir = 0.0f;
    private Float altitude = 0.0f;
    private Float velocity = 0.0f;
    private final List<GPSSatellite> gpsSatelliteList = new ArrayList<>();
    private GPSDOP gpsDop;
    
    public void updatefix() {
        fixed = quality > 0;
    }

    public String getSentenceType() {
        return sentenceType;
    }

    public void setSentenceType(String sentenceType) {
        this.sentenceType = sentenceType;
    }

    public Float getTime() {
        return time;
    }

    public void setTime(Float time) {
        this.time = time;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Boolean getFixed() {
        return fixed;
    }

    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Float getDir() {
        return dir;
    }

    public void setDir(Float dir) {
        this.dir = dir;
    }

    public Float getAltitude() {
        return altitude;
    }

    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public Float getVelocity() {
        return velocity;
    }

    public void setVelocity(Float velocity) {
        this.velocity = velocity;
    }

    public List<GPSSatellite> getGpsSatelliteList() {
        return gpsSatelliteList;
    }

    public GPSDOP getGpsDop() {
        return gpsDop;
    }

    public void setGpsDop(GPSDOP gpsDop) {
        this.gpsDop = gpsDop;
    }

    @Override
    public String toString() {
        return "GPSInformation{" +
                "sentenceType='" + sentenceType + '\'' +
                ", time=" + time +
                ", lat=" + lat +
                ", lon=" + lon +
                ", fixed=" + fixed +
                ", quality=" + quality +
                ", dir=" + dir +
                ", altitude=" + altitude +
                ", velocity=" + velocity +
                ", gpsSatelliteList=" + gpsSatelliteList +
                ", gpsDop=" + gpsDop +
                '}';
    }
}
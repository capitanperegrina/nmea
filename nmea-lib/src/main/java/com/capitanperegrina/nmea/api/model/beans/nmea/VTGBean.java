package com.capitanperegrina.nmea.api.model.beans.nmea;

import net.sf.marineapi.nmea.util.FaaMode;

public class VTGBean {

    private Double magneticCourse;
    private FaaMode mode;
    private Double speedKmh;
    private Double speedKnots;
    private Double trueCourse;

    public Double getMagneticCourse() {
        return magneticCourse;
    }

    public void setMagneticCourse(Double magneticCourse) {
        this.magneticCourse = magneticCourse;
    }

    public FaaMode getMode() {
        return mode;
    }

    public void setMode(FaaMode mode) {
        this.mode = mode;
    }

    public Double getSpeedKmh() {
        return speedKmh;
    }

    public void setSpeedKmh(Double speedKmh) {
        this.speedKmh = speedKmh;
    }

    public Double getSpeedKnots() {
        return speedKnots;
    }

    public void setSpeedKnots(Double speedKnots) {
        this.speedKnots = speedKnots;
    }

    public Double getTrueCourse() {
        return trueCourse;
    }

    public void setTrueCourse(Double trueCourse) {
        this.trueCourse = trueCourse;
    }

    @Override
    public String toString() {
        return "VTGBean{" +
                "magneticCourse=" + magneticCourse +
                ", mode=" + mode +
                ", speedKmh=" + speedKmh +
                ", speedKnots=" + speedKnots +
                ", trueCourse=" + trueCourse +
                '}';
    }
}

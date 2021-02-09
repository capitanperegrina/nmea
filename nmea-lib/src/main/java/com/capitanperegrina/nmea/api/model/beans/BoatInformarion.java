package com.capitanperegrina.nmea.api.model.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class BoatInformarion extends BoatPosition {

    private Double sog;
    private Double smoothSog;
    private Double cog;

    private Double milesFromLast;
    private Double hoursFromLast;

    public BoatInformarion(Date date) {
        super(date);
    }

    public BoatInformarion(Double lat, Double lon, Date date) {
        super(lat, lon, date);
    }

    public BoatInformarion(Double lat, Double lon, String name, Date date) {
        super(lat, lon, name, date);
    }

    public Double getSog() {
        return sog;
    }

    public void setSog(Double sog) {
        this.sog = sog;
    }

    public Double getSmoothSog() {
        return smoothSog;
    }

    public void setSmoothSog(Double smoothSog) {
        this.smoothSog = smoothSog;
    }

    public Double getCog() {
        return cog;
    }

    public void setCog(Double cog) {
        this.cog = cog;
    }

    public Double getMilesFromLast() {
        return milesFromLast;
    }

    public void setMilesFromLast(Double milesFromLast) {
        this.milesFromLast = milesFromLast;
    }

    public Double getHoursFromLast() {
        return hoursFromLast;
    }

    public void setHoursFromLast(Double hoursFromLast) {
        this.hoursFromLast = hoursFromLast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BoatInformarion that = (BoatInformarion) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(sog, that.sog)
                .append(smoothSog, that.smoothSog)
                .append(cog, that.cog)
                .append(milesFromLast, that.milesFromLast)
                .append(hoursFromLast, that.hoursFromLast)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(sog)
                .append(smoothSog)
                .append(cog)
                .append(milesFromLast)
                .append(hoursFromLast)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BoatInformarion{" +
                ", sog=" + sog +
                ", smoothCog=" + smoothSog +
                ", cog=" + cog +
                ", milesFromLast=" + milesFromLast +
                ", hoursFromLast=" + hoursFromLast +
                ", date=" + date +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

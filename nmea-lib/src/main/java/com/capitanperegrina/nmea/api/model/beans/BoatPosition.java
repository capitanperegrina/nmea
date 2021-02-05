package com.capitanperegrina.nmea.api.model.beans;

import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class BoatPosition extends Point {
    public Date date;

    public BoatPosition(Date date) {
        this.date = date;
    }

    public BoatPosition(Double lat, Double lon, Date date) {
        super(lat, lon);
        this.date = date;
    }

    public BoatPosition(Double lat, Double lon, String name, Date date) {
        super(lat, lon, name);
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BoatPosition that = (BoatPosition) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(date, that.date)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(date)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BoatPosition{" +
                "date=" + date +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}

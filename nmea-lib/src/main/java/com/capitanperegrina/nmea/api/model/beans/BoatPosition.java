package com.capitanperegrina.nmea.api.model.beans;

import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BoatPosition extends Point {
    public final String iso8601Date;

    public BoatPosition(String iso8601Date) {
        this.iso8601Date = iso8601Date;
    }

    public BoatPosition(Double lat, Double lon, String iso8601Date) {
        super(lat, lon);
        this.iso8601Date = iso8601Date;
    }

    public BoatPosition(Double lat, Double lon, String name, String iso8601Date) {
        super(lat, lon, name);
        this.iso8601Date = iso8601Date;
    }

    public String getIso8601Date() {
        return iso8601Date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BoatPosition that = (BoatPosition) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(iso8601Date, that.iso8601Date)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(iso8601Date)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BoatPosition{" +
                "date=" + iso8601Date +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}

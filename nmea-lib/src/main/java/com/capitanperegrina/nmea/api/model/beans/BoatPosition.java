package com.capitanperegrina.nmea.api.model.beans;

import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Line;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class BoatPosition extends Point {
    public final Date date;
    protected Double sog ;
    protected Double cog ;

    public BoatPosition() {
        this.date = null;
    }

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

    public Double getSog() {
        return sog;
    }

    public void setSog(Double sog) {
        this.sog = sog;
    }

    public Double getCog() {
        return cog;
    }

    public void setCog(Double cog) {
        this.cog = cog;
    }

    public Date getDate() {
        return date;
    }

    public Double diferenceInHours( Date otherDate ) {
        if ( this.date.compareTo(otherDate) > 0 ) {
            return new Long(otherDate.getTime() - this.date.getTime()).doubleValue() / 3600000;
        }
        return null;
    }

    public Double getSog(BoatPosition other) {
        Double distanceInNauticalMiles = this.distanceInNauticalMiles(other);
        Double timeInHours = this.diferenceInHours(other.getDate());
        if ( distanceInNauticalMiles != null && distanceInNauticalMiles != Double.NaN &&
                timeInHours != null && timeInHours != Double.NaN) {
            return distanceInNauticalMiles / timeInHours;
        }
        return null;
    }

    public Double getCog(BoatPosition other) {
        return new Line(this,other).getCog();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BoatPosition that = (BoatPosition) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(date, that.date)
                .append(sog, that.sog)
                .append(cog, that.cog)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(date)
                .append(sog)
                .append(cog)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BoatPosition{" +
                "date=" + date +
                ", sog=" + sog +
                ", cog=" + cog +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public static void main(String[] args) {

        BoatPosition p1 = new BoatPosition();
        p1.setLatitude(42.43336633333333);
        p1.setLongitude(-8.859328833333333);

        BoatPosition p2 = new BoatPosition();
        p2.setLatitude(42.433367833333335);
        p2.setLongitude(-8.859328833333333);

        System.out.println( new Line(p1, p2).getCog() );
    }
}

package com.capitanperegrina.nmea.api.model.entity;

import java.io.Serializable;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * POJO identificado con la tabla <code>trackpoint</code>
 */
public class TrackPointEntity implements Serializable
{
    private static final long serialVersionUID = -38261321158L;

    protected Integer idTrackPoint ;
    protected Date tsp ;
    protected Double lat ;
    protected Double lon ;
    protected Double sog ;
    protected Double cog ;

    public TrackPointEntity() {
    }

    public TrackPointEntity(Integer idTrackPoint, Date tsp, Double lat, Double lon, Double sog, Double cog) {
        this.idTrackPoint = idTrackPoint;
        this.tsp = tsp;
        this.lat = lat;
        this.lon = lon;
        if ( sog != null && Double.NaN != sog ) {
            this.sog = sog;
        }
        if ( cog != null && Double.NaN != cog ) {
            this.cog = cog;
        }
    }

    public Integer getIdTrackPoint() {
        return idTrackPoint;
    }

    public void setIdTrackPoint(Integer idTrackPoint) {
        this.idTrackPoint = idTrackPoint;
    }

    public Date getTsp() {
        return tsp;
    }

    public void setTsp(Date tsp) {
        this.tsp = tsp;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TrackPointEntity that = (TrackPointEntity) o;

        return new EqualsBuilder()
                .append(idTrackPoint, that.idTrackPoint)
                .append(tsp, that.tsp)
                .append(lat, that.lat)
                .append(lon, that.lon)
                .append(sog, that.sog)
                .append(cog, that.cog)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(idTrackPoint)
                .append(tsp)
                .append(lat)
                .append(lon)
                .append(sog)
                .append(cog)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "TrackpointPOJO{" +
                "idTrackPoint=" + idTrackPoint +
                ", tsp=" + tsp +
                ", lat=" + lat +
                ", lon=" + lon +
                ", sog=" + sog +
                ", cog=" + cog +
                '}';
    }
}
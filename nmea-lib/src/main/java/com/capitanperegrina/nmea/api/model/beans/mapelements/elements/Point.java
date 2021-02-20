package com.capitanperegrina.nmea.api.model.beans.mapelements.elements;

import com.capitanperegrina.nmea.api.model.beans.mapelements.MapElement;
import com.capitanperegrina.nmea.api.model.naming.GeoNaming;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Point extends MapElement {

    private static final long serialVersionUID = -3187729654488159968L;

    protected Double latitude;
    protected Double longitude;

    public Point() {
        super();
        this.type = MapElementType.POINT;
    }

    public Point(Double lat, Double lon) {
        this.latitude = lat;
        this.longitude = lon;
        this.type = MapElementType.POINT;
    }

    public Point(Double lat, Double lon, String name) {
        this.latitude = lat;
        this.longitude = lon;
        this.name = name;
        this.type = MapElementType.POINT;
    }

    public boolean isValid() {
        return this.getLatitude() != null && this.getLatitude() != null &&
                this.latitude >= -90 && this.latitude <= 90 &&
                this.longitude >= -180 && this.longitude <= 180;
    }

    @Override
    public Point centralPosition() {
        return this;
    }

    @Override
    public Double distanceInMeters(MapElement otherElement) {
        if (otherElement instanceof Point) {
            return this.distanceInMeters((Point) otherElement);
        }
        if (otherElement instanceof Line) {
            return otherElement.distanceInMeters(this);
        } else {
            return Double.NaN;
        }
    }

	@Override
	public Double distanceInNauticalMiles(MapElement otherElement) {
		if (otherElement instanceof Point) {
			return this.distanceInNauticalMiles((Point) otherElement);
		}
		if (otherElement instanceof Line) {
			return otherElement.distanceInNauticalMiles(this);
		} else {
			return Double.NaN;
		}
	}

	private Double distanceInNauticalMiles(Point otherPoint) {
		return distanceInMeters(otherPoint) / GeoNaming.NAUTICAL_MILE_IN_METERS;
	}

	private Double distanceInNauticalMiles(Line line) {
		return distanceInMeters(line) / GeoNaming.NAUTICAL_MILE_IN_METERS;
	}

    private Double distanceInMeters(Point otherPoint) {
        Double lat1 = Math.toRadians(this.latitude);
        Double lon1 = Math.toRadians(this.longitude);
        Double lat2 = Math.toRadians(otherPoint.getLatitude());
        Double lon2 = Math.toRadians(otherPoint.getLongitude());

        Double dlon = lon2 - lon1;
        Double dlat = lat2 - lat1;

        Double sinlat = Math.sin(dlat / 2);
        Double sinlon = Math.sin(dlon / 2);

        Double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2) * (sinlon * sinlon);
        Double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

        Double distanceInMeters = GeoNaming.EARTH_RADIUS * c * 1000;

        return Math.abs(distanceInMeters);
    }

    public Double heading(Point otherPoint) {
        Point pointA = new Point();
        pointA.setLatitude(this.latitude);
        pointA.setLongitude(otherPoint.getLongitude());

        Double dx = pointA.distanceInMeters(this);
        Double dy = pointA.distanceInMeters(otherPoint);

        Double angle = Math.atan2(dy, dx) * 180 / Math.PI;
        angle = 90 - angle;

        int cuadrante = this.longitude <= otherPoint.getLongitude() ? this.latitude <= otherPoint.getLatitude() ? 3 : 2
                : this.latitude <= otherPoint.getLatitude() ? 1 : 0;

        angle = angle + 90 * cuadrante;

        if (angle < 0) {
            angle = angle + 360;
        }

        if (angle > 360) {
            angle = angle - 360;
        }

        return angle;
    }

    @Override
    public Point rotate(Double angulo, Point anchor) {
        Double rad = Math.toRadians(angulo);

        Point2D.Double ini = new Point2D.Double(this.longitude, this.latitude);
        Point2D.Double ret = new Point2D.Double();

        AffineTransform rotation = new AffineTransform();
        rotation.rotate(rad, anchor.getLongitude(), anchor.getLatitude());
        rotation.transform(ini, ret);
        return new Point(ret.getX(), ret.getY());
    }

    @Override
    public Point cornerNW() {
        return this;
    }

    @Override
    public Point cornerNE() {
        return this;
    }

    @Override
    public Point cornerSW() {
        return this;
    }

    @Override
    public Point cornerSE() {
        return this;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Point point = (Point) o;

		return new EqualsBuilder()
				.append(latitude, point.latitude)
				.append(longitude, point.longitude)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.appendSuper(super.hashCode())
				.append(latitude)
				.append(longitude)
				.toHashCode();
	}

	@Override
	public String toString() {
		return "Point{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				", name='" + name + '\'' +
				", type=" + type +
				'}';
	}
}

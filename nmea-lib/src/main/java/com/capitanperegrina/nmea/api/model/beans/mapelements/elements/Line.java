package com.capitanperegrina.nmea.api.model.beans.mapelements.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.capitanperegrina.nmea.api.model.beans.mapelements.MapElement;
import com.capitanperegrina.nmea.api.model.naming.GeoNaming;
import com.capitanperegrina.nmea.api.model.naming.MapElementType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Line extends MapElement implements Serializable {

    private long serialVersionUID = -2986129642357285810L;

    protected Point point1;
    protected Point point2;

    public Line(double lat1, double lon1, double lat2, double lon2) {
        Point a = new Point(lat1, lon1);
        Point b = new Point(lat2, lon2);
        this.point1 = a;
        this.point2 = b;
        this.type = MapElementType.LINE;
    }

    public Line(Point p1, Point p2) {
        this.point1 = p1;
        this.point2 = p2;
        this.type = MapElementType.LINE;
    }

    public Line(double lat1, double lon1, double lat2, double lon2, String name) {
        Point a = new Point(lat1, lon1);
        Point b = new Point(lat2, lon2);
        this.point1 = a;
        this.point2 = b;
        this.name = name;
        this.type = MapElementType.LINE;
    }

    public Line(Point p1, Point p2, String name) {
        this.point1 = p1;
        this.point2 = p2;
        this.name = name;
        this.type = MapElementType.LINE;
    }

    public Point getPoint1() {
        return this.point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return this.point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public double slope() {
        return (this.point2.getLatitude() - this.point1.getLatitude()) / (this.point2.getLongitude() - this.point1.getLongitude());
    }

    public double getCog() {
        if (this.point1.equals(this.point2)) {
            return 0d;
        }
        int cuadrante = this.getQuadrant();
        double cog = 0;
        if (cuadrante == 1) {
            cog = this.getAngle();
        } else if (cuadrante == 2) {
            cog = 90 + (90 - this.getAngle());
        } else if (cuadrante == 3) {
            cog = 180 + this.getAngle();
        } else {
            cog = 270 + (90 - this.getAngle());
        }
        if (cog > 360) {
            cog = cog - 360;
        }
        return cog;
    }

    private int getQuadrant() {
        double x, y;
        x = this.point2.getLongitude() - this.point1.getLongitude();
        y = this.point2.getLatitude() - this.point1.getLatitude();

        if (x >= 0) {
            if (y >= 0) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (y >= 0) {
                return 4;
            } else {
                return 3;
            }
        }
    }

    /**
     * <pre>
     *                        /       ( v1x * v2x ) + ( v1y * v2y )     \
     *   angle(v1, v2) = acos | --------------------------------------- |
     *                        |      _____________       _____________  |
     *                        |  \  /    2      2    \  /    2      2   |
     *                        \   \/  v1x  + v1y   *  \/  v2x  + v2y    /
     * </pre>
     */
    public double getAngle() {
        double angulo = Math.toDegrees(Math.atan((this.point2.getLongitude() - this.point1.getLongitude())
                / (this.point2.getLatitude() - this.point1.getLatitude())));
        return Math.abs(angulo);
    }

    public double getAngle(MapElement mel) {
        if (mel instanceof Point) {
            Point p = (Point) mel;
            double angulo1 = this.getAngle();
            double angulo2 = new Line(this.point1, p).getAngle();
            double ret = angulo2 - angulo1;
            if (ret < -180) {
                ret = ret + 180;
            } else if (ret > 180) {
                ret = ret - 180;
            }
            return ret;
        } else if (mel instanceof Line) {
            Line l = (Line) mel;
            double r1 = l.getAngle(l.getPoint1());
            double r2 = l.getAngle(l.getPoint2());
            if (Math.abs(r1) <= Math.abs(r2)) {
                return r1;
            } else {
                return r2;
            }
        } else {
            return Double.NaN;
        }
    }

    @Override
    public Line rotate(Double angulo, Point anchor) {
        return new Line(this.point1.rotate(angulo, anchor), this.point2.rotate(angulo, anchor));
    }

    @Override
    public Point centralPosition() {
        return new Point(
                ((this.point1.getLatitude() + this.point2.getLatitude()) / 2),
                ((this.point1.getLongitude() + this.point2.getLongitude()) / 2));
    }

	private double distanceInNauticalMiles(Point otherPoint) {
		return distanceInMeters(otherPoint) / GeoNaming.NAUTICAL_MILE_IN_METERS;
	}

	private double distanceInNauticalMiles(Line line) {
		return distanceInMeters(line) / GeoNaming.NAUTICAL_MILE_IN_METERS;
	}

	public Double distanceInNauticalMiles(MapElement otherElement) {
		if (otherElement instanceof Point) {
			return distanceInNauticalMiles((Point) otherElement);
		} else if (otherElement instanceof Line) {
			return distanceInNauticalMiles((Line) otherElement);
		} else {
			return Double.NaN;
		}
	}

    @Override
	public Double distanceInMeters(MapElement otherElement) {
        if (otherElement instanceof Point) {
            return distanceInMeters((Point) otherElement);
        } else if (otherElement instanceof Line) {
            return distanceInMeters((Line) otherElement);
        } else {
            return Double.NaN;
        }
    }

    public double distanceInMeters(Point point) {
        return point.distanceInMeters(this.closestPoint(point));
    }

    public double distanceInMeters(Line otherLine) {
        if (this.doIntersect(otherLine)) {
            return 0d;
        }
        List<Double> lista = new ArrayList<>(4);
        lista.add(this.distanceInMeters(otherLine.getPoint1()));
        lista.add(this.distanceInMeters(otherLine.getPoint2()));
        lista.add(otherLine.distanceInMeters(this.getPoint1()));
        lista.add(otherLine.distanceInMeters(this.getPoint2()));
        Collections.sort(lista);
        return lista.get(0);
    }

    /**
     * Extraido de http://polplabs.net/blog/2011/02/03/calcular-si-dos-rectas-se-cortan-entre-sus-limites/
     *
     * @param otraLinea
     * @return
     */
    public boolean doIntersect(Line otraLinea) {
        // linea 1 va desde (x1,y1) a (x2,y2)
        double x1 = this.getPoint1().getLongitude();
        double y1 = this.getPoint1().getLatitude();
        double x2 = this.getPoint2().getLongitude();
        double y2 = this.getPoint2().getLatitude();

        // linea 2 va desde (x3,y3) a (x4,y4)
        double x3 = otraLinea.getPoint1().getLongitude();
        double y3 = otraLinea.getPoint1().getLatitude();
        double x4 = otraLinea.getPoint2().getLongitude();
        double y4 = otraLinea.getPoint2().getLatitude();

        double temp;
        // Ordenamos las lineas para que la menor x sea el primer punto
        if (x1 > x2) {
            temp = x2;
            x2 = x1;
            x1 = temp;
            temp = y2;
            y2 = y1;
            y1 = temp;
        }

        if (x3 > x4) {
            temp = x4;
            x4 = x3;
            x3 = temp;
            temp = y4;
            y4 = y3;
            y3 = temp;
        }
        // Ya las tenemos ordenadas

        double pendiente1 = 0;
        double pendiente2 = 0;

        if ((y2 - y1) != 0) {
            pendiente1 = (y2 - y1) / (x2 - x1); // pendiente recta 1
        }

        if ((y3 - y4) != 0) {
            pendiente2 = (y4 - y3) / (x4 - x3); // pendiente recta 2
        }

        double ordenada_en_origen1 = y1 - pendiente1 * x1; // corte con eje y
        // linea 1
        double ordenada_en_origen2 = y3 - pendiente2 * x3; // corte con eje y
        // linea 2

        // puntos de corte entre las lineas
        double corte_x = (ordenada_en_origen2 - ordenada_en_origen1) / (pendiente1 - pendiente2);
        // double corte_y = ( ordenada_en_origen1 + pendiente1 * corte_x );

        // comprobamos si esta en el rango dentro de la recta
        if (x1 <= corte_x && x2 >= corte_x && x3 <= corte_x && x4 >= corte_x) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <pre>
     * Obtenemos el punto de la recta perpendicular al punto fuera de la misma.
     *       |-------- u1 -------|   |--------- u2 --------|
     *       ( Cx - Ax )*( Bx-Ax ) + ( Cy - Ay )*( By - Ay )
     * u = ---------------------------------------------------
     *                 ( Bx - Ax ) + ( By -Ay )
     *                 |--------- u3 ---------|
     *
     * Cx = Ax + u(Bx-Ax)
     * Cy = Ay + u(By-Ay)
     * </pre>
     *
     * @param point
     * @return
     */
    public Point closestPoint(Point point) {
        double u1 = (point.getLongitude() - this.getPoint1().getLongitude())
                * (this.getPoint2().getLongitude() - this.getPoint1().getLongitude());

        double u2 = (point.getLatitude() - this.getPoint1().getLatitude())
                * (this.getPoint2().getLatitude() - this.getPoint1().getLatitude());

        double u3 = Math.pow((this.getPoint2().getLongitude() - this.getPoint1().getLongitude()), 2)
                + Math.pow((this.getPoint2().getLatitude() - this.getPoint1().getLatitude()), 2);

        double u = (u1 + u2) / u3;

        Point p = new Point();
        if (u <= 0) {
            // Si es menor que 0, C ha sobrepasado el segmento por A.
            return this.getPoint1();
        } else if (u >= 1) {
            // Si es mayor que 1, C ha sobrepasado el segmento por B.
            return this.getPoint2();
        } else {
            // Si u es menor que 0, el punto C no ha llegado al segmento.
            // Cx = Ax + u(Bx-Ax)
            p.setLongitude(this.getPoint1().getLongitude()
                    + u * (this.getPoint2().getLongitude() - this.getPoint1().getLongitude()));
            // Cy = Ay + u(By-Ay)
            p.setLatitude(this.getPoint1().getLatitude()
                    + u * (this.getPoint2().getLatitude() - this.getPoint1().getLatitude()));
            return p;
        }
    }

    @Override
    public Point cornerNW() {
        return new Point(
                Math.min(this.point1.getLongitude(), this.point2.getLongitude()),
                Math.max(this.point1.getLatitude(), this.point2.getLatitude()));
    }

    @Override
    public Point cornerNE() {
        return new Point(
                Math.max(this.point1.getLongitude(), this.point2.getLongitude()),
                Math.max(this.point1.getLatitude(), this.point2.getLatitude()));
    }

    @Override
    public Point cornerSW() {
        return new Point(
                Math.min(this.point1.getLongitude(), this.point2.getLongitude()),
                Math.min(this.point1.getLatitude(), this.point2.getLatitude()));
    }

    @Override
    public Point cornerSE() {
        return new Point(
                Math.max(this.point1.getLongitude(), this.point2.getLongitude()),
                Math.min(this.point1.getLatitude(), this.point2.getLatitude()));
    }


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Line line = (Line) o;

		return new EqualsBuilder()
				.appendSuper(super.equals(o))
				.append(serialVersionUID, line.serialVersionUID)
				.append(point1, line.point1)
				.append(point2, line.point2)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.appendSuper(super.hashCode())
				.append(serialVersionUID)
				.append(point1)
				.append(point2)
				.toHashCode();
	}

	@Override
	public String toString() {
		return "Line{" +
				"point1=" + point1 +
				", point2=" + point2 +
				", name='" + name + '\'' +
				", type=" + type +
				'}';
	}
}

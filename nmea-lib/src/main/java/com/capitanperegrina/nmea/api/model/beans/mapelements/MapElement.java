package com.capitanperegrina.nmea.api.model.beans.mapelements;

import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import com.capitanperegrina.nmea.api.model.naming.MapElementType;

import java.io.Serializable;

public abstract class MapElement implements Serializable {

    private static final long serialVersionUID = 4826351660869399141L;

    protected String name;
    protected MapElementType type;

    public MapElement() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MapElementType getType() {
        return this.type;
    }

    public void setType(MapElementType type) {
        this.type = type;
    }

    public abstract Point centralPosition();

	public abstract Double distanceInNauticalMiles(MapElement otherElement);

    public abstract Double distanceInMeters(MapElement otherElement);

    public abstract Point cornerNW();

    public abstract Point cornerNE();

    public abstract Point cornerSW();

    public abstract Point cornerSE();

    public abstract MapElement rotate(Double angulo, Point anchor);

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MapElement other = (MapElement) obj;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (this.type != other.type)
            return false;
        return true;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MapElement [name=").append(this.name).append(", type=").append(this.type).append("]");
        return builder.toString();
    }
}

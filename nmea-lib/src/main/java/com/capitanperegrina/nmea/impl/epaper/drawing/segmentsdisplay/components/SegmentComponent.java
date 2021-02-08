package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.javatuples.Pair;

public class SegmentComponent {

    private PolygonType polygonType;
    private List<Pair<Integer,Integer>> listaCoordenadas;

    public SegmentComponent(PolygonType type, List<Pair<Integer, Integer>> listaCoordenadas) {
        this.polygonType = type;
        this.listaCoordenadas = listaCoordenadas;
    }

    public PolygonType getPolygonType() {
        return polygonType;
    }

    public void setPolygonType(PolygonType polygonType) {
        this.polygonType = polygonType;
    }

    public List<Pair<Integer, Integer>> getListaCoordenadas() {
        return listaCoordenadas;
    }

    public void setListaCoordenadas(List<Pair<Integer, Integer>> listaCoordenadas) {
        this.listaCoordenadas = listaCoordenadas;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SegmentComponent that = (SegmentComponent) o;

        return new EqualsBuilder().append(polygonType, that.polygonType).append(listaCoordenadas,
                that.listaCoordenadas).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(polygonType).append(listaCoordenadas).toHashCode();
    }

    @Override
    public String toString() {
        return "SegmentComponent{" +
                "type=" + polygonType +
                ", listaCoordenadas=" + listaCoordenadas +
                '}';
    }
}

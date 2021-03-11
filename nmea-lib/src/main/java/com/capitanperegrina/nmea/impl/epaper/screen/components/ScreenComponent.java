package com.capitanperegrina.nmea.impl.epaper.screen.components;

import org.javatuples.Pair;

public class ScreenComponent {

    private Pair<Integer, Integer> offset;
    private String value;
    private String oldValue;
    private final Integer scale;
    private boolean displayed;

    public ScreenComponent(final Pair<Integer, Integer> offset, final Integer scale, final String value, final String oldValue, final boolean displayed) {
        this.offset = offset;
        this.scale = scale;
        this.value = value;
        this.oldValue = oldValue;
        this.displayed = displayed;
    }

    public Pair<Integer, Integer> getOffset() {
        return this.offset;
    }

    public void setOffset(final Pair<Integer, Integer> offset) {
        this.offset = offset;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getOldValue() {
        return this.oldValue;
    }

    public void setOldValue(final String oldValue) {
        this.oldValue = oldValue;
    }

    public Integer getScale() {
        return this.scale;
    }

    public boolean isDisplayed() {
        return this.displayed;
    }

    public void setDisplayed(final boolean displayed) {
        this.displayed = displayed;
    }

    @Override
    public String toString() {
        return "ScreenComponent{" +
                "offset=" + this.offset +
                ", value='" + this.value + '\'' +
                ", oldValue='" + this.oldValue + '\'' +
                ", scale=" + this.scale +
                ", displayed=" + this.displayed +
                '}';
    }
}

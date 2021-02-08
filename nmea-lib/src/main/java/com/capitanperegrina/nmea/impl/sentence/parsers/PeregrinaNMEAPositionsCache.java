package com.capitanperegrina.nmea.impl.sentence.parsers;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;

import java.util.LinkedList;

public class PeregrinaNMEAPositionsCache {

    private static volatile PeregrinaNMEAPositionsCache singleton;

    private final LinkedList<BoatPosition> positionsFifo = new LinkedList<>();

    private PeregrinaNMEAPositionsCache(){
        if (singleton != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static PeregrinaNMEAPositionsCache getInstance() {
        if (singleton == null) {
            synchronized (PeregrinaNMEADisplay.class) {
                if (singleton == null) singleton = new PeregrinaNMEAPositionsCache();
            }
        }
        return singleton;
    }

    public void addPosition(BoatPosition position) {
        this.positionsFifo.add(position);
        if ( this.positionsFifo.size() > 100 ) {
            this.positionsFifo.removeFirst();
        }
    }

    /*
    public Double getSmoothSpeed(int seconds) {
        int elementsUsed = this.positionsFifo.size();
        if ( elementsUsed > 10 ) {
            elementsUsed = 10;
        }
        this.positionsFifo.get(elementsUsed).distanceInNauticalMiles(this.positionsFifo.get(0)) /
    }
    */
}
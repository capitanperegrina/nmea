package com.capitanperegrina.nmea.impl.core;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;

public class PeregrinaNMEADataBuffer {

    private static volatile PeregrinaNMEADataBuffer singleton;
    private static Integer DATA_BUFFER_SIZE = 10;

    private final SortedMap<String, BoatPosition> boatInformationMap = new TreeMap<>();


    private PeregrinaNMEADataBuffer(){
        if (singleton != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static PeregrinaNMEADataBuffer getInstance() {
        if (singleton == null) {
            synchronized (PeregrinaNMEADisplay.class) {
                if (singleton == null) singleton = new PeregrinaNMEADataBuffer();
            }
        }
        return singleton;
    }

    public Map<String, BoatPosition> getBoatInformationMap() {
        return boatInformationMap;
    }

    public void addElement(BoatPosition boatPosition) {
        this.boatInformationMap.put(boatPosition.getIso8601Date(), boatPosition);
        if ( this.boatInformationMap.size() > DATA_BUFFER_SIZE ) {
            this.boatInformationMap.remove(this.boatInformationMap.lastKey());
        }
        System.out.println(this.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.boatInformationMap.entrySet().stream().forEach( e -> {
            sb.append(e.getKey()).append(" -> ").append(e.getValue().toString()).append("\n");
        });
        return sb.toString();
    }
}
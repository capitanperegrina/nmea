package com.capitanperegrina.nmea.impl.core;

import java.util.LinkedList;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;

public class PeregrinaNMEADataBuffer {

    private static volatile PeregrinaNMEADataBuffer singleton;
    private static Integer DATA_BUFFER_SIZE = 10;

    private final LinkedList<BoatPosition> boatPositionList = new LinkedList<>();
    private final LinkedList<Double> boatSpeedList = new LinkedList<>();
    private Point waypoint;

    private PeregrinaNMEADataBuffer() {
        if (singleton != null) {
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

    public void setWaypoint(Point point) {
        this.waypoint = point;
    }

    public void addPostion(BoatPosition boatPosition) {
        // Making space if full
        this.boatPositionList.add(boatPosition);
        if (this.boatPositionList.size() > DATA_BUFFER_SIZE) {
            this.boatPositionList.removeFirst();
        }

        // Quick and dirty stuff
        Double vmg = Double.NaN;
        Double dtw = Double.NaN;
        if ( waypoint != null && waypoint.isValid() ) {
            dtw = boatPosition.distanceInNauticalMiles( waypoint );
            if ( this.boatPositionList.size() > 1 ) {
                vmg = boatPosition.distanceInNauticalMiles(this.boatPositionList.get(this.boatPositionList.size() - 2))
                        /
                        boatPosition.diferenceInHours(this.boatPositionList.get(this.boatPositionList.size() - 2).getDate());
            }
        }
        System.out.println("************************************************************************************************************************************");
        System.out.println(this.toPostionString());
        System.out.println("Waypoint: " +   this.waypoint.toString());
        System.out.println("DTW = " + PeregrinaNMEAUtils.speedFormat(dtw) + " Nm.");
        System.out.println("VMG = " + PeregrinaNMEAUtils.speedFormat(vmg) + " Kn.\n");
    }

    public void addSpeed(Double speed) {
        // Making space if full
        this.boatSpeedList.add(speed);
        if (this.boatSpeedList.size() > DATA_BUFFER_SIZE) {
            this.boatSpeedList.removeFirst();
        }

        // Quick and dirty stuff
        System.out.println(this.toSpeedsString());
    }

    public String toPostionString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "Cached positions: (").append( this.boatPositionList.size()).append(")\n" );
        this.boatPositionList.stream().forEach( position -> {
            sb.append(PeregrinaNMEAUtils.boatInformarionToFormattedString(position)).append("\n" );
        });
        return sb.toString();
    }

    public String toSpeedsString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "Cached speeds: (").append( this.boatSpeedList.size()).append(")\n" );
        this.boatSpeedList.stream().forEach( speed -> {
            sb.append(PeregrinaNMEAUtils.speedFormat(speed)).append(", " );
        });
        sb.append("[" );
        sb.append(PeregrinaNMEAUtils.speedFormat(this.boatSpeedList.stream().filter(d -> d != null && !d.equals(Double.NaN)).
                mapToDouble(d -> d).average().orElse(Double.NaN)));
        sb.append("]");
        return sb.toString();
    }
}
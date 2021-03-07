package com.capitanperegrina.nmea.impl.core;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import com.capitanperegrina.nmea.api.model.naming.WaypointsNaming;
import com.capitanperegrina.nmea.api.model.service.ITrackService;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeregrinaNMEADataBuffer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeregrinaNMEADataBuffer.class);

    private static volatile PeregrinaNMEADataBuffer singleton;
    private static Integer DATA_BUFFER_SIZE = 10;

    private final LinkedList<BoatPosition> boatPositionList = new LinkedList<>();
    private final LinkedList<Double> boatSpeedList = new LinkedList<>();
    private int currentWaypoint = -1;
    private Point waypoint;
    private ITrackService trackService;

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

    public void setTrackService(ITrackService trackService) {
        this.trackService = trackService;
    }

    public void setWaypoint(Point point) {
        this.waypoint = point;
    }

    public int getCurrentWaypoint() {
        return currentWaypoint;
    }

    public void setCurrentWaypoint(int newWaypoint) {
        this.currentWaypoint = newWaypoint;
        this.waypoint = WaypointsNaming.getInternalWaypoints().get(currentWaypoint);
        LOGGER.info("New waypoint: {}", this.waypoint.toString());
    }

    public void addPostion(BoatPosition boatPosition) {
        // Making space if full
        this.boatPositionList.add(boatPosition);
        if (this.boatPositionList.size() > DATA_BUFFER_SIZE) {
            this.boatPositionList.removeFirst();
        }

        // Calculate sog and cog from previous point.
        Double sog = null;
        Double cog = null;
        if (this.boatPositionList.size() > 1) {
            this.boatPositionList.get(this.boatPositionList.size() - 1).setSog(this.boatPositionList.get(this.boatPositionList.size() - 1).getSog(this.boatPositionList.get(this.boatPositionList.size() - 2)));
            this.boatPositionList.get(this.boatPositionList.size() - 1).setCog(this.boatPositionList.get(this.boatPositionList.size() - 1).getCog(this.boatPositionList.get(this.boatPositionList.size() - 2)));
        }

        // Quick and dirty stuff
        Double vmg = Double.NaN;
        Double dtw = Double.NaN;

        LOGGER.debug(PeregrinaNMEAUtils.boatInformarionToFormattedString(boatPosition));

        if ( waypoint != null && waypoint.isValid() ) {
            dtw = boatPosition.distanceInNauticalMiles( waypoint );
            if ( this.boatPositionList.size() > 1 ) {
                vmg = boatPosition.distanceInNauticalMiles(this.boatPositionList.get(this.boatPositionList.size() - 2))
                        /
                        boatPosition.diferenceInHours(this.boatPositionList.get(this.boatPositionList.size() - 2).getDate());
            }
            LOGGER.info("Waypoint: {}    DTW = {} Nm.    VMC = {} Kn.", this.waypoint.toString(), PeregrinaNMEAUtils.speedFormat(dtw), PeregrinaNMEAUtils.speedFormat(vmg));
        }
        this.trackService.savePoint(boatPosition);
    }

    public void addSpeed(Double speed) {
        // Making space if full
        this.boatSpeedList.add(speed);
        if (this.boatSpeedList.size() > DATA_BUFFER_SIZE) {
            this.boatSpeedList.removeFirst();
        }

        Double sog = boatSpeedList.stream().filter(value -> value != null || value != Double.NaN).mapToDouble(a -> a).average().orElse(0d);
        PeregrinaNMEADisplay.getInstance().draw16segments( new Pair<>(155,195), 5, sog);

        // Quick and dirty stuff
        LOGGER.debug(this.toSpeedsString());
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
        sb.append( "Cached speeds: (").append( this.boatSpeedList.size()).append(") : " );
        this.boatSpeedList.stream().forEach( speed -> {
            sb.append(PeregrinaNMEAUtils.speedFormat(speed)).append(", " );
        });
        sb.append(" Smooth Spedd: " );
        sb.append(PeregrinaNMEAUtils.speedFormat(this.boatSpeedList.stream().filter(d -> d != null && !d.equals(Double.NaN)).
                mapToDouble(d -> d).average().orElse(Double.NaN)));
        sb.append("");
        return sb.toString();
    }
}
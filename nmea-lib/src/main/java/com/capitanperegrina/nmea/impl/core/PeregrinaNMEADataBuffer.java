package com.capitanperegrina.nmea.impl.core;

import java.util.LinkedList;

import com.capitanperegrina.nmea.api.model.beans.BoatInformarion;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Line;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;

public class PeregrinaNMEADataBuffer {

    private static volatile PeregrinaNMEADataBuffer singleton;
    private static Integer DATA_BUFFER_SIZE = 10;

    private final LinkedList<BoatInformarion> boatInformarionList = new LinkedList<>();

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

    public void addElement(BoatInformarion boatInformarion) {
        // Making space if full
        this.boatInformarionList.add(boatInformarion);
        if (this.boatInformarionList.size() > DATA_BUFFER_SIZE) {
            this.boatInformarionList.removeFirst();
        }

        // Calculations
        if ( this.boatInformarionList.size() > 1 ) {
            this.boatInformarionList.get(this.boatInformarionList.size()-1).setMilesFromLast(this.boatInformarionList.get(this.boatInformarionList.size()-1).distanceInNauticalMiles(this.boatInformarionList.get(this.boatInformarionList.size()-2)));
            this.boatInformarionList.get(this.boatInformarionList.size()-1).setHoursFromLast(this.boatInformarionList.get(this.boatInformarionList.size()-1).diferenceInHours(this.boatInformarionList.get(this.boatInformarionList.size()-2).getDate()));
            this.boatInformarionList.get(this.boatInformarionList.size()-1).setSog(this.boatInformarionList.get(this.boatInformarionList.size()-1).getMilesFromLast()/this.boatInformarionList.get(this.boatInformarionList.size()-1).getHoursFromLast());
            this.boatInformarionList.get(this.boatInformarionList.size()-1).setCog(new Line(this.boatInformarionList.get(this.boatInformarionList.size()-2),this.boatInformarionList.get(this.boatInformarionList.size()-1)).getCog());
            double elements = 0;
            double sum = 0;
            for ( int i = 0; i < this.boatInformarionList.size()-1 ; i++ ) {
                if ( this.boatInformarionList.get(i) != null && this.boatInformarionList.get(i).getSog() != null ) {
                    sum = sum + this.boatInformarionList.get(i).getSog();
                    elements++;
                }
            }
            this.boatInformarionList.get(this.boatInformarionList.size()-1).setSmoothSog(sum/elements);
            System.out.printf("* %2d ***********************************************************************************************************************************\n", this.boatInformarionList.size());
            System.out.println(this.toString());
        } else {
            System.out.printf("* %2d ***********************************************************************************************************************************\n", this.boatInformarionList.size());
            System.out.println(this.toString());
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.boatInformarionList.size(); i++) {
            sb.append(i).append(" - ").append(PeregrinaNMEAUtils.boatInformarionToFormattedString(this.boatInformarionList.get(i))).append("\n");
        }
        return sb.toString();
    }
}
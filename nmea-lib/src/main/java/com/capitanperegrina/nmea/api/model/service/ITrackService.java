package com.capitanperegrina.nmea.api.model.service;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;

import java.util.Date;
import java.util.List;

public interface ITrackService {

    void savePoint(BoatPosition bp );

    List<BoatPosition> readTrack();
}

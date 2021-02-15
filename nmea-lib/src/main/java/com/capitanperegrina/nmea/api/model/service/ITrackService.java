package com.capitanperegrina.nmea.api.model.service;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;

import java.io.IOException;
import java.util.List;

public interface ITrackService {

    void savePoint(BoatPosition bp );

    List<BoatPosition> readTrack();

    void generateGpxFile();

    void cleanData();
}

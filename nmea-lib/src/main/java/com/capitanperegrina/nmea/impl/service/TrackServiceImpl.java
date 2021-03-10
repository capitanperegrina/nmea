package com.capitanperegrina.nmea.impl.service;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.entity.ITrackPointDao;
import com.capitanperegrina.nmea.api.model.entity.TrackPointEntity;
import com.capitanperegrina.nmea.api.model.service.ITrackService;
import io.jenetics.jpx.*;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements ITrackService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TrackServiceImpl.class);

    @Autowired
    private ITrackPointDao trackpointDao;

    @Override
    @Transactional
    public void savePoint(BoatPosition bp) {
        BoatPosition normalizedForDatabaseBp = normalizeForDatabase(bp);
        this.trackpointDao.add(
            new TrackPointEntity(
                0,
                normalizedForDatabaseBp.getDate(),
                normalizedForDatabaseBp.getLatitude(),
                normalizedForDatabaseBp.getLongitude(),
                normalizedForDatabaseBp.getSog(),
                normalizedForDatabaseBp.getCog()));
    }

    @Override
    public List<BoatPosition> readTrack() {
        List<TrackPointEntity> list = this.trackpointDao.find(null);
        return list.stream()
            .map(tpe -> new BoatPosition(tpe.getLat(), tpe.getLon(), tpe.getTsp()))
            .collect(Collectors.toList());
    }

    @Override
    public void generateGpxFile() {
        // Filename
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        String fileName = sdf.format(date) + ".gpx";
        LOGGER.debug("Filename generated {}", fileName);

        // ReadPoints
        List<TrackPointEntity> trackPoints = this.trackpointDao.find(null);
        LOGGER.debug("{} points readed from database", trackPoints.size());

        // Generate GPX
        LOGGER.debug("Building GPX object... ");
        TrackSegment.Builder tsBuilder = TrackSegment.builder();
        for (TrackPointEntity tp : trackPoints) {
            tsBuilder.addPoint(WayPoint.builder().lat(tp.getLat()).lon(tp.getLon()).ele(0).build());
        }
        Track track = Track.builder().name("Peregrina Nmea Track").addSegment(tsBuilder.build()).build();
        final GPX gpx = GPX.builder()
            .metadata(
                Metadata.builder().time(date.getTime()).name("PeregrinaNMEA Export").build())
            .addTrack(track)
            .build();
        LOGGER.debug("GPX generated.");

        try {
            LOGGER.debug("Writing GPX file... ");
            GPX.write(gpx, Paths.get(fileName));
            LOGGER.debug("OK");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanData() {
        this.trackpointDao.emptyTable();
    }

    public BoatPosition normalizeForDatabase(BoatPosition bp) {
        bp.setLatitude(normalizeForDatabase(bp.getLatitude()));
        bp.setLongitude(normalizeForDatabase(bp.getLongitude()));
        bp.setSog(normalizeForDatabase(bp.getSog()));
        bp.setCog(normalizeForDatabase(bp.getCog()));
        return bp;
    }

    public Double normalizeForDatabase(Double d) {
        if (d != null && d.equals(Double.NaN)) {
            return null;
        }
        return d;
    }

}

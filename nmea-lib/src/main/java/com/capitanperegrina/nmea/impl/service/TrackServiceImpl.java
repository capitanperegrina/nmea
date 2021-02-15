package com.capitanperegrina.nmea.impl.service;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.entity.ITrackPointDao;
import com.capitanperegrina.nmea.api.model.entity.TrackPointEntity;
import com.capitanperegrina.nmea.api.model.service.ITrackService;
import io.jenetics.jpx.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements ITrackService {

    @Autowired
    private ITrackPointDao trackpointDao;

    @Override
    @Transactional
    public void savePoint(BoatPosition bp) {
        this.trackpointDao.add(new TrackPointEntity(0, bp.getDate(), bp.getLatitude(), bp.getLongitude(), null, null));
    }

    @Override
    public List<BoatPosition> readTrack() {
        List<TrackPointEntity> list = this.trackpointDao.find(null);
        return list.stream().map(tpe -> new BoatPosition(tpe.getLat(), tpe.getLon(), tpe.getTsp())).collect(Collectors.toList());
    }

    @Override
    public void generateGpxFile() {
        // Filename
        System.out.print("Generating filename...");
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        String text = sdf.format(date) + ".gpx";
        System.out.println("OK -> " + text);

        // ReadPoints
        System.out.print("Reading points from database... ");
        List<TrackPointEntity> trackPoints = this.trackpointDao.find(null);
        System.out.println("OK -> " + trackPoints.size() + " points readed.");

        // Generate GPX
        System.out.print("Building GPX object... ");
        TrackSegment.Builder tsBuilder = TrackSegment.builder();
        trackPoints.stream().map(tp -> tsBuilder.addPoint( p -> p.lat(tp.getLat()).lon(tp.getLon()).ele(0) ) );
        Track track = Track.builder().name("Peregrina Nmea Track").addSegment( tsBuilder.build() ).build();
        final GPX gpx = GPX.builder()
            .addTrack(track)
                .build();
        System.out.println("OK");

        try {
            System.out.print("Writing GPX file... ");
            GPX.write(gpx, Paths.get("track.gpx"));
            System.out.println("OK");
        } catch ( IOException e ) {
            System.out.println("ERROR : ");
            e.printStackTrace();
        }
    }

    @Override
    public void cleanData() {
        this.trackpointDao.emptyTable();
    }
}

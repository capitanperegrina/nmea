package com.capitanperegrina.nmea.impl.service;

import com.capitanperegrina.nmea.api.model.beans.BoatPosition;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import com.capitanperegrina.nmea.api.model.entity.ITrackPointDao;
import com.capitanperegrina.nmea.api.model.entity.TrackPointEntity;
import com.capitanperegrina.nmea.api.model.service.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
}

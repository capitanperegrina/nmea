package com.capitanperegrina.nmea.api.model.entity;

import java.util.List;

public interface ITrackPointDao {
    void add(TrackPointEntity obj);

    boolean exists(TrackPointEntity obj);

    TrackPointEntity read(TrackPointEntity obj);

    void update(TrackPointEntity obj);

    void delete(TrackPointEntity obj);

    List<TrackPointEntity> find(TrackPointEntity obj);
}

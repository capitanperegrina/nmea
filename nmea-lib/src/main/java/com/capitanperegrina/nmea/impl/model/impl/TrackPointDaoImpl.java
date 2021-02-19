package com.capitanperegrina.nmea.impl.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capitanperegrina.nmea.api.model.entity.ITrackPointDao;
import com.capitanperegrina.nmea.api.model.entity.TrackPointEntity;
import com.capitanperegrina.utils.sql.GenericRepository;
import com.capitanperegrina.utils.sql.QueryUtils;
import com.capitanperegrina.utils.sql.ResultSetUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Objeto de acceso a datos para la tabla <code>trackpoint<code>
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS)
public class TrackPointDaoImpl extends GenericRepository implements ITrackPointDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackPointDaoImpl.class);

    public static final String WHERE_BY_KEY = " id_track_point = ?  ";

    public static final String ENTITY = TrackPointEntity.class.getName();
    public static final String TABLE = "trackpoint";
    public static final String FIELDS_ALL = "id_track_point, tsp, lat, lon, sog, cog ";
    public static final String FIELDS_INSERT = "?, ?, ?, ?, ?, ? ";
    public static final String FIELDS_UPDATE = " tsp = ?, lat = ?, lon = ?, sog = ?, cog = ? ";
    public static final String FIELDS_PRIMARY_KEY = " id_track_point = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class TrackpointRowMapper implements RowMapper<TrackPointEntity> {
        @Override
        public TrackPointEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            TrackPointEntity obj = new TrackPointEntity();
            obj.setIdTrackPoint(ResultSetUtils.getInteger("id_track_point", rs));
            obj.setTsp(ResultSetUtils.getDate("tsp", rs));
            obj.setLat(ResultSetUtils.getDouble("lat", rs));
            obj.setLon(ResultSetUtils.getDouble("lon", rs));
            obj.setSog(ResultSetUtils.getDouble("sog", rs));
            obj.setCog(ResultSetUtils.getDouble("cog", rs));
            return obj;
        }
    }

    private static Object[] toParamsAll( TrackPointEntity obj ) {
        return ArrayUtils.addAll( toParamsKey( obj ) , toParamsRest( obj ) );
    }

    private static Object[] toParamsUpdate( TrackPointEntity obj ) {
        return ArrayUtils.addAll( toParamsRest( obj ), toParamsKey( obj ) );
    }

    private static Object[] toParamsKey( TrackPointEntity obj ) {
        return new Object[] {
            obj.getIdTrackPoint()
         };
    }

    private static Object[] toParamsRest( TrackPointEntity obj ) {
        return new Object[] {
            obj.getTsp(),
            obj.getLat(),
            obj.getLon(),
            obj.getSog(),
            obj.getCog()
        };
    }

    private TrackpointRowMapper getRowMapper() {
        return new TrackpointRowMapper();
    }

    @Override
    public void add(TrackPointEntity obj) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsAll(obj);
        q.append(generateInsertQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public boolean exists(TrackPointEntity obj) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
    public TrackPointEntity read(TrackPointEntity obj) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, getRowMapper());
    }

    @Override
    public void update(TrackPointEntity obj) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsUpdate(obj);
		q.append(generateUpdateQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.queryLog(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public void delete(TrackPointEntity obj) {
        StringBuilder q = new StringBuilder();
		Object[] p = toParamsKey(obj);
		q.append(generateDeleteQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.queryLog(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    private List<TrackPointEntity> searchAll() {
        StringBuilder q = new StringBuilder();
        Object[] p = {};
        q.append(generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), getRowMapper());
    }

    @Override
    public List<TrackPointEntity> find(TrackPointEntity obj) {
        if ( obj == null ) {
            return searchAll();
        }

        StringBuilder cond = new StringBuilder(" 1=1 ");
        List<Object> parametros = new ArrayList<>();

        if ( obj.getIdTrackPoint() != null ) {
            cond.append( "   AND id_track_point = ? " );
            parametros.add( obj.getIdTrackPoint() );
        }
        if ( obj.getTsp() != null ) {
            cond.append( "   AND tsp = ? " );
            parametros.add( obj.getTsp() );
        }
        if ( obj.getLat() != null ) {
            cond.append( "   AND lat = ? " );
            parametros.add( obj.getLat() );
        }
        if ( obj.getLon() != null ) {
            cond.append( "   AND lon = ? " );
            parametros.add( obj.getLon() );
        }
        if ( obj.getSog() != null ) {
            cond.append( "   AND sog = ? " );
            parametros.add( obj.getSog() );
        }
        if ( obj.getCog() != null ) {
            cond.append( "   AND cog = ? " );
            parametros.add( obj.getCog() );
        }

        Object[] p = parametros.toArray();
        StringBuilder q = new StringBuilder();
        q.append(generateSelectQuery( cond.toString() ));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, getRowMapper());
    }

    public void emptyTable() {
        StringBuilder q = new StringBuilder();
        Object[] p = new Object[] {};
        q.append("TRUNCATE TABLE " + TABLE);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    protected String getTable() {
        return TABLE;
    }

    @Override
    protected String getFields() {
        return FIELDS_ALL;
    }

    @Override
    protected String getInsertQuestionMarks() {
        return FIELDS_INSERT;
    }

    @Override
    protected String getWhereByKey() {
        return FIELDS_PRIMARY_KEY;
    }

    @Override
    protected String getFieldsUpdate() {
       return FIELDS_UPDATE;
    }
}
package com.capitanperegrina.nmea.api.model.beans.nmea;

import net.sf.marineapi.nmea.util.SatelliteInfo;

import java.util.List;
import java.util.stream.Collectors;

public class GSVBean {

    private Integer satelliteCount;
    private List<SatelliteInfo> satelliteInfo;
    private Integer sentenceCount;
    private Integer sentenceIndex;
    private Boolean first;
    private Boolean last;

    public Integer getSatelliteCount() {
        return satelliteCount;
    }

    public void setSatelliteCount(Integer satelliteCount) {
        this.satelliteCount = satelliteCount;
    }

    public List<SatelliteInfo> getSatelliteInfo() {
        return satelliteInfo;
    }

    public void setSatelliteInfo(List<SatelliteInfo> satelliteInfo) {
        this.satelliteInfo = satelliteInfo;
    }

    public Integer getSentenceCount() {
        return sentenceCount;
    }

    public void setSentenceCount(Integer sentenceCount) {
        this.sentenceCount = sentenceCount;
    }

    public Integer getSentenceIndex() {
        return sentenceIndex;
    }

    public void setSentenceIndex(Integer sentenceIndex) {
        this.sentenceIndex = sentenceIndex;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GSVBean{");
        sb.append("satelliteCount=" + satelliteCount);
        sb.append(", satelliteInfo=" + satelliteInfo.stream().map(si -> {
            return "\n   SatelliteInfo{" +
                    "id='" + si.getId() + '\'' +
                    ", elevation=" + si.getElevation() +
                    ", azimuth=" + si.getAzimuth() +
                    ", noise=" + si.getNoise() +
                    '}';
        }).collect(Collectors.toList()).toString());
        sb.append(", sentenceCount=" + sentenceCount);
        sb.append(", sentenceIndex=" + sentenceIndex);
        sb.append(", first=" + first);
        sb.append(", last=" + last);
        sb.append('}');
        return sb.toString();
    }
}

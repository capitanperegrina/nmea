package com.capitanperegrina.nmea.api.model.beans;

/**
 *  1    = Total number of messages of this type in this cycle
 *  2    = Message number
 *  3    = Total number of SVs in view
 *  4    = SV PRN number
 *  5    = Elevation in degrees, 90 maximum
 *  6    = Azimuth, degrees from true north, 000 to 359
 *  7    = SNR, 00-99 dB (null when not tracking)
 *  8-11 = Information about second SV, same as field 4-7
 *  12-15= Information about third SV, same as field 4-7
 *  16-19= Information about fourth SV, same as field 4-7
 */
public class GPSSatellite {

    private Integer totalMessages;
    private Integer number;
    private Integer svInViewNumber;

    private Integer svPrnNumber;
    private Integer elevation;
    private Integer azimuth;
    private Integer snr;

    public Integer getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(Integer totalMessages) {
        this.totalMessages = totalMessages;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSvInViewNumber() {
        return svInViewNumber;
    }

    public void setSvInViewNumber(Integer svInViewNumber) {
        this.svInViewNumber = svInViewNumber;
    }

    public Integer getSvPrnNumber() {
        return svPrnNumber;
    }

    public void setSvPrnNumber(Integer svPrnNumber) {
        this.svPrnNumber = svPrnNumber;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public Integer getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(Integer azimuth) {
        this.azimuth = azimuth;
    }

    public Integer getSnr() {
        return snr;
    }

    public void setSnr(Integer snr) {
        this.snr = snr;
    }

    @Override
    public String toString() {
        return "GPSSatellite{" +
                "totalMessages=" + totalMessages +
                ", number=" + number +
                ", svInViewNumber=" + svInViewNumber +
                ", svPrnNumber=" + svPrnNumber +
                ", elevation=" + elevation +
                ", azimuth=" + azimuth +
                ", snr=" + snr +
                '}';
    }
}

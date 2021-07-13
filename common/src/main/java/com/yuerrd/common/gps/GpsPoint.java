package com.yuerrd.common.gps;

/**
 * @author yuerrd
 */
public class GpsPoint {
    private Double lon;
    private Double Lat;

    public GpsPoint() {
    }

    public GpsPoint(Double lon, Double lat) {
        this.lon = lon;
        this.Lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        this.Lat = lat;
    }
}

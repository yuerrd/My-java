package com.yuerrd.common.gps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuerrd
 */
public class GpsMain {

    private static final double THRESHOLD = 0.00001D;

    Map<String, GpsPoint[]> gpsMaps= new ConcurrentHashMap<>();

    GpsPoint[] gpsPoints = new GpsPoint[2];

    public GpsPoint getGpsPoint(String vin, GpsPoint gpsPoint) {
        GpsPoint[] gpsPoints = gpsMaps.get(vin);
        GpsPoint res = null;
        if (gpsPoints[0] == null) {
            gpsPoints[0] = gpsPoint;
            return res;
        }
        if (gpsPoints[1] == null) {
            gpsPoints[1] = gpsPoint;
            return res;
        }

        double distance = perpendicularDistance(gpsPoints[1], gpsPoints[0], gpsPoint);
        if (distance > THRESHOLD) {
            res = gpsPoints[0];
            gpsPoints[0] = gpsPoints[1];
        }
        gpsPoints[1] = gpsPoint;
        return res;
    }


   static  double perpendicularDistance(GpsPoint pt, GpsPoint lineStart, GpsPoint lineEnd) {
        double dx = lineEnd.getLon() - lineStart.getLon();
        double dy = lineEnd.getLat() - lineStart.getLat();

        // Normalize
        double mag = Math.hypot(dx, dy);
        if (mag > 0.0) {
            dx /= mag;
            dy /= mag;
        }
        double pvx = pt.getLon() - lineStart.getLon();
        double pvy = pt.getLat() - lineStart.getLat();

        // Get dot product (project pv onto normalized direction)
        double pvdot = dx * pvx + dy * pvy;

        // Scale line direction vector and subtract it from pv
        double ax = pvx - pvdot * dx;
        double ay = pvy - pvdot * dy;
        return Math.hypot(ax, ay);
    }

    public static void main(String[] args) {
        GpsPoint point1 = new GpsPoint(116.381514D, 39.976678D);
        GpsPoint point2 = new GpsPoint(116.405911,   39.97699);
        GpsPoint point3 = new GpsPoint(116.394238,   39.976957);
        System.out.println(perpendicularDistance(point3,point1,point2));
        System.out.println(1000);
    }
}

package com.yuerrd.common.gps;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * @author yuerrd
 */
public class DistanceCalculator {

    private static final double R = 6371; // km
    private static final double PI = 3.14159265358979323846;

    public static void main(String[] args) throws java.lang.Exception {
        // 116.381085,39.976809
        // 116.381342,39.97671
        // 116.394646,39.968521

        System.out.println(distance(39.976678, 116.381514, 39.97699, 116.405911));
        System.out.println(distance(39.976678, 116.381514, 39.976957, 116.394238));
        System.out.println(distance(39.976957, 116.394238,39.968538,116.39471));
    }


    static double distance(double dLat1, double dLon1, double dLat2, double dLon2) {

        // Use Haversine formula to calculate distance (in km) between two points specified by
        // latitude/longitude (in numeric degrees).
        // It is the shortest distance over the earth surface.
        double dLat = (dLat2 - dLat1) * (PI / 180.0);
        double dLon = (dLon2 - dLon1) * (PI / 180.0);
        double a = sin(dLat / 2) * sin(dLat / 2) +
                cos(dLat1 * (PI / 180.0)) * cos(dLat2 * (PI / 180.0)) *
                        sin(dLon / 2) * sin(dLon / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        // Distance in km
        double d = R * c;

        // Return distance in meters
        return d * 1000.0;
    }
}

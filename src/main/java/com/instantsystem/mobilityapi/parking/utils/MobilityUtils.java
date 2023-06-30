package com.instantsystem.mobilityapi.parking.utils;

import lombok.experimental.UtilityClass;

/**
 * Utility class for mobility operations
 * 
 * @author jearfi
 */
@UtilityClass
public class MobilityUtils {

    /**
     * Allows to calculate the distance between two points in latitude and
     * longitude taking into account height difference. Uses Haversine method as
     * its base.
     * 
     * @param latStartPoint latitude Start point
     * @param lonStartPoint longitude Start point
     * @param latEndPoint latitude End point
     * @param lonEndPoint longitude End point
     * @returns Distance in Meters
     */
    public static double getDistanceBetweenTwoPoints(double latStartPoint, double latEndPoint, double lonStartPoint,
            double lonEndPoint) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(latEndPoint - latStartPoint);
        double lonDistance = Math.toRadians(lonEndPoint - lonStartPoint);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latStartPoint)) * Math.cos(Math.toRadians(latEndPoint))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}

package com.instantsystem.mobilityapi.parking.entity;

import lombok.Data;

/**
 * Entity class that represents a parking
 * 
 * @author jearfi
 */
@Data
public class Parking implements Comparable<Parking> {

    private String id;
    private String name;
    private String address;
    private Double xlong;
    private Double ylat;
    private String desc;
    private String url;
    private Long nbAvailableSlots;
    private Double occupationRate;
    private Long capacity;
    private Long nbSlots;
    private Long nbOccupiedSlots;
    private Double distanceFromPositionInMeters;

    @Override
    public int compareTo(Parking parking) {
        if (getDistanceFromPositionInMeters() == null || parking.getDistanceFromPositionInMeters() == null) {
            return 0;
        }
        return getDistanceFromPositionInMeters().compareTo(parking.getDistanceFromPositionInMeters());
    }
}

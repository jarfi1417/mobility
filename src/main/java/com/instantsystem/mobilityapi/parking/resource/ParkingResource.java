package com.instantsystem.mobilityapi.parking.resource;

import lombok.Data;

/**
 * Entity that represents the parking resource returned by the API
 * 
 * @author jearfi
 */
@Data
public class ParkingResource {

    private String id;
    private String name;
    private String desc;
    private String address;
    private String url;
    private Double xlong;
    private Double ylat;
    private Long nbAvailableSlots;
    private Double occupationRate;
    private Long capacity;
    private Long nbOccupiedSlots;
    private Double distanceFromPositionInMeters;
}

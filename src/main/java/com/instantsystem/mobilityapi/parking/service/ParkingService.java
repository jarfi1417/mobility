package com.instantsystem.mobilityapi.parking.service;

import java.util.List;

import com.instantsystem.mobilityapi.parking.entity.Parking;
import com.instantsystem.mobilityapi.parking.exception.MobilityException;

/**
 * Parking service
 * 
 * @author jearfi
 */
public interface ParkingService {

    /**
     * Allows to get a list of {@link Parking} closest to the given coordinates
     * (longitude xlong and latitude ylat) and if available or not
     * 
     * @param fromLongitude the longitude
     * @param fromLatitude the latitude
     * @param withAvailableSlots <code>true</code> to request only parkings with
     * available slots, <code>false</code> otherwise.
     * @return the list of {@link Parking} according to the given parameters
     * @throws MobilityException if an error occurred
     */
    public List<Parking> getAllClosestParkings(String fromLatitude, String fromLongitude, Boolean withAvailableSlots) throws MobilityException;
}

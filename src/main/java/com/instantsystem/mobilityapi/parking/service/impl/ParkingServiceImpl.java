/**
 * 
 */
package com.instantsystem.mobilityapi.parking.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.instantsystem.mobilityapi.parking.config.YAMLConfig;
import com.instantsystem.mobilityapi.parking.entity.Parking;
import com.instantsystem.mobilityapi.parking.entity.Root;
import com.instantsystem.mobilityapi.parking.exception.MobilityException;
import com.instantsystem.mobilityapi.parking.exception.MobilityNotValidException;
import com.instantsystem.mobilityapi.parking.mapper.ParkingMapper;
import com.instantsystem.mobilityapi.parking.service.ParkingService;
import com.instantsystem.mobilityapi.parking.utils.JsonUtils;
import com.instantsystem.mobilityapi.parking.utils.MobilityUtils;

/**
 * Implementation class of Parking service
 * 
 * @author jearfi
 */
@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private YAMLConfig yamlConfig;

    @Autowired
    private ParkingMapper parkingMapper;

    @Override
    public List<Parking> getAllClosestParkings(String fromLatitude, String fromLongitude, Boolean withAvailableSlots) throws MobilityException {

        validate(fromLatitude, fromLongitude);

        // Get the full parking info
        List<Parking> parkings = getFullParkingInfo(
                fromLatitude,
                fromLongitude,
                getAllParkingsWithDetails(),
                getAllParkingAvailabilityInfo());

        // To get only parkings with available slots
        if (withAvailableSlots != null
                && Boolean.TRUE.equals(withAvailableSlots)
                && !CollectionUtils.isEmpty(parkings)) {
            parkings = parkings.stream().filter(parking -> parking.getOccupationRate() != null && parking.getOccupationRate() > 0).collect(Collectors.toList());
        }

        // Order parkings by distance from the given point ascending
        if (!CollectionUtils.isEmpty(parkings)) {
            Collections.sort(parkings);
        }

        return parkings;
    }

    /**
     * Allows to validate the coordinates
     * 
     * @param fromLatitude the latitude to validate
     * @param fromLongitude the longitude to validate
     * @throws MobilityNotValidException the validation failed
     */
    private void validate(String fromLatitude, String fromLongitude) throws MobilityNotValidException {
        if ((StringUtils.isBlank(fromLatitude) && StringUtils.isNotBlank(fromLongitude))
                || (StringUtils.isNotBlank(fromLatitude) && StringUtils.isBlank(fromLongitude))) {
            throw new MobilityNotValidException("The latitude and longitude should be both either not null or null.");
        }

        if (StringUtils.isNotBlank(fromLatitude)) {
            try {
                Double.parseDouble(fromLatitude);
            } catch (NumberFormatException nfe) {
                throw new MobilityNotValidException("The latitude value is not valid : " + fromLatitude, nfe);
            }
        }

        if (StringUtils.isNotBlank(fromLongitude)) {
            try {
                Double.parseDouble(fromLongitude);
            } catch (NumberFormatException nfe) {
                throw new MobilityNotValidException("The longitude value is not valid : " + fromLongitude, nfe);
            }
        }

    }

    /**
     * Allows to get a list of parkings with details
     * 
     * @return a list of the parkings with details
     * @throws MobilityException if an error occurred
     */
    private List<Parking> getAllParkingsWithDetails() throws MobilityException {

        // Get url from config file
        String parkingsListUrl = yamlConfig.getParkingsListUrl();

        // Deserialize the JSON string obtained from the URL   
        Root allParkingsInfo = JsonUtils.jsonToRoot(parkingsListUrl);

        return parkingMapper.asParkingList(allParkingsInfo.getRecords());
    }

    /**
     * Allows to get a list of parkings with availability information
     * 
     * @return a list of parkings with availability information
     * @throws MobilityException if an error occurred
     */
    private List<Parking> getAllParkingAvailabilityInfo() throws MobilityException {

        // Get url from config file
        String availableSlotsUrl = yamlConfig.getAvailableSlotsUrl();

        // Deserialize the JSON string obtained from the URL
        Root allParkingsAvailabilityInfo = JsonUtils.jsonToRoot(availableSlotsUrl);

        return parkingMapper.asParkingList(allParkingsAvailabilityInfo.getRecords());
    }

    /**
     * Allows to get the full parkings with details and availability information
     * when available
     * 
     * @param allParkingsWithDetails a list of the parkings with details
     * @param allParkingsAvailabilityInfo a list of parkings with availability
     * information
     * @return the full parkings with details and availability information when
     * available
     * @throws MobilityNotValidException if an error occured validating the
     * coordinates
     */
    private List<Parking> getFullParkingInfo(
            String fromLatitude,
            String fromLongitude,
            List<Parking> allParkingsWithDetails,
            List<Parking> allParkingsAvailabilityInfo) throws MobilityNotValidException {

        // Merge the two data sets in one with the needed information
        List<Parking> mergedParkings = Stream
                .concat(allParkingsWithDetails.stream(), allParkingsAvailabilityInfo.stream())
                .collect(Collectors.toList());

        // Fill the capacity field if null with nbSlots value
        mergedParkings.forEach(parking -> {
            if (parking.getCapacity() == null) {
                parking.setCapacity(parking.getNbSlots());
            }
        });

        // Merge the entities from the two list when common values (by parking name)
        Map<String, Parking> parkingMap = mergedParkings.stream()
                .collect(Collectors.toMap(Parking::getName, Function.identity(), (parking1, parking2) -> {
                    parking1.setOccupationRate(parking2.getOccupationRate());
                    parking1.setNbOccupiedSlots(parking2.getNbOccupiedSlots());
                    if (parking1.getCapacity() != null && parking1.getNbOccupiedSlots() != null) {
                        parking1.setNbAvailableSlots(parking1.getCapacity() - parking1.getNbOccupiedSlots());
                    }

                    return parking1;
                }));

        List<Parking> allParkings = new ArrayList<>(parkingMap.values());

        // If the latitude and longitude are provided then calculate the distance from each parking coordinates
        if (fromLatitude != null && fromLongitude != null) {
            Double latitude = Double.parseDouble(fromLatitude);
            Double longitude = Double.parseDouble(fromLongitude);
            for (Parking parking : allParkings) {
                parking.setDistanceFromPositionInMeters(
                        MobilityUtils.getDistanceBetweenTwoPoints(latitude, parking.getYlat(), longitude, parking.getXlong()));
            }
        }

        // Returned only parkings with coordinates
        return allParkings.stream().filter(parking -> parking.getXlong() > 0 && parking.getYlat() > 0).collect(Collectors.toList());
    }

}

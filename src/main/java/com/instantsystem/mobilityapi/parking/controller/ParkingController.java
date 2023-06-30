package com.instantsystem.mobilityapi.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.instantsystem.mobilityapi.parking.entity.Parking;
import com.instantsystem.mobilityapi.parking.exception.MobilityException;
import com.instantsystem.mobilityapi.parking.exception.MobilityNotValidException;
import com.instantsystem.mobilityapi.parking.mapper.ParkingMapper;
import com.instantsystem.mobilityapi.parking.resource.ParkingResource;
import com.instantsystem.mobilityapi.parking.service.ParkingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controller to manage the parking services
 * 
 * @author jearfi
 */
@Api(value = "ParkingController")
@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingMapper parkingMapper;

    /**
     * Allows to get a list of {@link ParkingResource} closest to the given
     * coordinates (longitude longitude and latitude latitude) and if available
     * or not.
     * 
     * @param longitude the longitude. Could be null
     * @param latitude the latitude. Could be null
     * @param withAvailableSlots <code>true</code> to request only parkings with
     * available slots, <code>false</code> otherwise. Could be null.
     * @return the list of {@link ParkingResource} according to the given
     * parameters
     */
    @ApiOperation(value = "Allows to get all closest parkings", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ParkingResource> getAllParkings(
            @RequestParam(required = false) String latitude,
            @RequestParam(required = false) String longitude,
            @RequestParam(required = false) Boolean withAvailableSlots) {
        try {
            // Get the parkings
            List<Parking> parkings = parkingService.getAllClosestParkings(latitude, longitude, withAvailableSlots);

            return parkingMapper.asParkingResourceList(parkings);
        } catch (MobilityNotValidException mnve) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "The request sent is not valid", mnve);
        } catch (MobilityException me) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "An error occured getting the parkings", me);
        }
    }
}

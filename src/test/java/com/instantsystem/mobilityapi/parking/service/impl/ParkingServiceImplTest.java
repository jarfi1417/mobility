package com.instantsystem.mobilityapi.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.instantsystem.mobilityapi.parking.exception.MobilityException;
import com.instantsystem.mobilityapi.parking.exception.MobilityNotValidException;
import com.instantsystem.mobilityapi.parking.service.ParkingService;

/**
 * Parking service test class
 * 
 * @author jearfi
 */
@SpringBootTest
class ParkingServiceImplTest {

    @Autowired
    private ParkingService parkingService;

    @Test
    void testGetAllClosestParkings() throws MobilityException {

        // Lat and Long of Poitiers City center
        assertNotNull(parkingService.getAllClosestParkings("46.580224", "0.340375", false));
    }

    @Test
    void testGetAllClosestParkingsWithoutCoordinatesGiven() throws MobilityException {

        // Lat and Long of Poitiers City center
        assertNotNull(parkingService.getAllClosestParkings(null, null, false));
    }

    @Test
    void testGetAllClosestParkingsAvailable() throws MobilityException {

        // Lat and Long of Poitiers City center
        assertNotNull(parkingService.getAllClosestParkings("46.580224", "0.340375", true));
    }

    @ParameterizedTest(name = "{index} => Get All Closest Parkings {0} from coordinates ({1}, {2}) throw error")
    @CsvSource({
            "With Null Longitude, 46.580224, , false, The latitude and longitude should be both either not null or null.",
            "With Not Valid Longitude, 46.580224, toto, false, The longitude value is not valid : toto",
            "With Not Valid Latitude, toto, 0.340375, false, The latitude value is not valid : toto",
    })
    void testGetAllClosestParkingsWithErrors(String desc, String lat, String lon, Boolean withAvailableSlots, String expectedMessage) {
        Exception exception = assertThrows(MobilityNotValidException.class, () -> {
            parkingService.getAllClosestParkings(lat, lon, withAvailableSlots);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}

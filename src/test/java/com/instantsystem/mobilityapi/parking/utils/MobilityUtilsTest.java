package com.instantsystem.mobilityapi.parking.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Mobility Utility test class
 * 
 * @author jearfi
 */
class MobilityUtilsTest {

    @Test
    void testGetDistanceBetweenTwoPoints() {
        double lat1 = 51.5007;
        double lon1 = 0.1246;
        double lat2 = 40.6892;
        double lon2 = 74.0445;

        double distance = MobilityUtils.getDistanceBetweenTwoPoints(lat1, lat2, lon1, lon2);
        assertEquals(5574840.456848553, distance);
    }

}

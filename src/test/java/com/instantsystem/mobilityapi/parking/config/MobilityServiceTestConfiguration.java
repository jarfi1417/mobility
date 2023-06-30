package com.instantsystem.mobilityapi.parking.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.instantsystem.mobilityapi.parking.mapper.ParkingMapper;

/**
 * Mobility configuration class for services
 * 
 * @author jearfi
 */
@ExtendWith(SpringExtension.class)
public abstract class MobilityServiceTestConfiguration {

    @Autowired
    protected ParkingMapper parkingMapper;
}

package com.instantsystem.mobilityapi.parking.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Mobility configuration class for API
 * 
 * @author jearfi
 */
@ExtendWith(SpringExtension.class)
public abstract class MobilityApiTestConfiguration {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper mapper;

    /**
     * Allows to perform get parking request
     * 
     * @param longitude the longitude
     * @param latitude the latitude
     * @param withAvailableSlots
     * @param expectedStatus the expected status returned by the request
     * @throws Exception if an error occurred
     */
    protected void testGetAllParking(String longitude, String latitude, String withAvailableSlots, ResultMatcher expectedStatus) throws Exception {
        mvc.perform(get("/parking?longitude=" + longitude + "&latitude=" + latitude + "&withAvailableSlots=" + withAvailableSlots)
                //.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(expectedStatus);
    }
}

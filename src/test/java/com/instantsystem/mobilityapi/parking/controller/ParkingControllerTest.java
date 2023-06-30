package com.instantsystem.mobilityapi.parking.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instantsystem.mobilityapi.parking.config.MobilityApiTestConfiguration;
import com.instantsystem.mobilityapi.parking.exception.MobilityException;
import com.instantsystem.mobilityapi.parking.exception.MobilityNotValidException;
import com.instantsystem.mobilityapi.parking.mapper.ParkingMapper;
import com.instantsystem.mobilityapi.parking.resource.ParkingResource;
import com.instantsystem.mobilityapi.parking.service.ParkingService;

/**
 * Unit test class for ParkingController
 * 
 * @author jearfi
 */
@ContextConfiguration(classes = { ParkingControllerTest.SpringTestConfig.class })
@WebMvcTest(ParkingController.class)
class ParkingControllerTest extends MobilityApiTestConfiguration {

    /**
     * The test configuration for this test class
     */
    @Configuration
    @ComponentScan(basePackageClasses = {
            ParkingControllerTest.class, ParkingMapper.class, ParkingController.class })
    public static class SpringTestConfig {

    }

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkingService parkingService;

    @Test
    void testGetAllParking() throws Exception {
        Mockito.when(parkingService.getAllClosestParkings(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean())).thenReturn(new ArrayList<>());

        MvcResult result = mvc.perform(get("/parking")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<ParkingResource> parkingResources = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ParkingResource>>() {
        });

        assertThat(parkingResources).isEmpty();
    }

    @Test
    void testGetAllParkingWithParameters() throws Exception {
        Mockito.when(parkingService.getAllClosestParkings(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean())).thenReturn(new ArrayList<>());

        MvcResult result = mvc.perform(get("/parking?longitude=45.515165151&latitude=0.51465111&withAvailableSlots=false")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<ParkingResource> parkingResources = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ParkingResource>>() {
        });

        assertThat(parkingResources).isEmpty();
    }

    @Test
    void testGetAllParkingInternalServeurError() throws Exception {
        Mockito.when(parkingService.getAllClosestParkings(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean())).thenThrow(new MobilityException("test error"));
        testGetAllParking("0.5165165165D", "45.1515151515D", "true", status().isInternalServerError());
    }

    @Test
    void testGetAllParkingBadRequest() throws Exception {
        Mockito.when(parkingService.getAllClosestParkings(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean())).thenThrow(new MobilityNotValidException("test error"));
        testGetAllParking("0.516165165D", "45.1515151515D", "true", status().isBadRequest());
    }

}

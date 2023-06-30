package com.instantsystem.mobilityapi.parking.controller.it;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.instantsystem.mobilityapi.parking.config.MobilityApiTestConfiguration;
import com.instantsystem.mobilityapi.parking.resource.ParkingResource;

/**
 * Integration test class for ParkingController
 * 
 * @author jearfi
 */
@SpringBootTest
@AutoConfigureMockMvc
class ParkingControllerTestIT extends MobilityApiTestConfiguration {

    @Test
    void testGetAllParking() throws Exception {
        MvcResult result = mvc.perform(get("/parking?longitude=45.515165151&latitude=0.51465111&withAvailableSlots=false")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<ParkingResource> parkingSlotResources = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ParkingResource>>() {
        });

        assertThat(parkingSlotResources).isNotEmpty().hasSize(26);
    }

    // TODO complete
    @Test
    void testGetAllAvailableParking() throws Exception {
        MvcResult result = mvc.perform(get("/parking?longitude=45.515165151&latitude=0.51465111&withAvailableSlots=true")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<ParkingResource> parkingSlotResources = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ParkingResource>>() {
        });

        assertThat(parkingSlotResources).isNotEmpty().hasSize(7);
    }

    @Test
    void testGetAllParkingNoValidRequestWrongLongitude() throws Exception {
        testGetAllParking(null, "45.1515151515", "true", status().isBadRequest());
    }

    @Test
    void testGetAllParkingNoValidRequestLatitudeNull() throws Exception {
        testGetAllParking("0.5555", null, "true", status().isBadRequest());
    }

}

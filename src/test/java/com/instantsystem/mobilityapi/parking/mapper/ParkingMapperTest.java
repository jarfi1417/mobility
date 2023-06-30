package com.instantsystem.mobilityapi.parking.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.instantsystem.mobilityapi.parking.config.MobilityServiceTestConfiguration;
import com.instantsystem.mobilityapi.parking.entity.Field;
import com.instantsystem.mobilityapi.parking.entity.Parking;
import com.instantsystem.mobilityapi.parking.entity.Record;
import com.instantsystem.mobilityapi.parking.resource.ParkingResource;

/**
 * Parking mapper test class
 * 
 * @author jearfi
 */
class ParkingMapperTest extends MobilityServiceTestConfiguration {

    /**
     * The test configuration for this test class
     */
    @Configuration
    @ComponentScan(basePackageClasses = {
            ParkingMapperTest.class, ParkingMapper.class })
    public static class SpringTestConfig {

    }

    @Test
    void testAsParking() {
        Record parkingRecord = createRecords(1).get(0);
        Parking parking = parkingMapper.asParking(parkingRecord);
        assertParking(parking);
    }

    @Test
    void testAsParkingList() {
        List<Parking> parkings = parkingMapper.asParkingList(createRecords(3));
        for (Parking parking : parkings) {
            assertParking(parking);
        }
    }

    @Test
    void testAsParkingResource() {
        Parking parking = createParkings(1).get(0);
        ParkingResource parkingResource = parkingMapper.asParkingResource(parking);
        assertParkingResource(parkingResource);
    }

    @Test
    void testAsParkingResourceList() {
        List<ParkingResource> parkingResources = parkingMapper.asParkingResourceList(createParkings(3));
        for (ParkingResource parkingResource : parkingResources) {
            assertParkingResource(parkingResource);
        }
    }

    /**
     * Allows to create a new list of {@link Record}
     * 
     * @param nbRecordsExpected the number of records expected
     * @return a new list of {@link Record}
     */
    private List<Record> createRecords(int nbRecordsExpected) {
        List<Record> records = new ArrayList<>();

        for (int i = 0; i < nbRecordsExpected; i++) {
            Record parkingRecord = new Record();
            Field fields = new Field();
            fields.setAdresse("Parking address");
            fields.setId("Parking id");
            fields.setInfo("Parking info");
            fields.setNom("Parking nom");
            fields.setUrl("Parking url");
            fields.setYlat(43.555);
            fields.setXlong(0.3987122);
            fields.setCapacite(70L);
            fields.setPlaces(50L);
            fields.setTauxOccupation(20.5);
            parkingRecord.setFields(fields);
            records.add(parkingRecord);
        }

        return records;
    }

    /**
     * Allows to create a new list of {@link Parking}
     * 
     * @param nbRecordsExpected the number of records expected
     * @return a new list of {@link Parking}
     */
    private List<Parking> createParkings(int nbRecordsExpected) {
        List<Parking> parkings = new ArrayList<>();

        for (int i = 0; i < nbRecordsExpected; i++) {
            Parking parking = new Parking();
            parking.setAddress("Parking address");
            parking.setId("Parking id");
            parking.setDesc("Parking info");
            parking.setName("Parking nom");
            parking.setUrl("Parking url");
            parking.setYlat(43.555);
            parking.setXlong(0.3987122);
            parking.setCapacity(70L);
            parking.setNbAvailableSlots(50L);
            parking.setOccupationRate(25.56);
            parking.setNbOccupiedSlots(20L);
            parkings.add(parking);
        }

        return parkings;
    }

    /**
     * Allows to assert a {@link Parking}
     * 
     * @param parking the parking entity to assert
     */
    private void assertParking(Parking parking) {
        assertNotNull(parking);
        assertNotNull(parking.getAddress());
        assertNotNull(parking.getId());
        assertNotNull(parking.getDesc());
        assertNotNull(parking.getName());
        assertNotNull(parking.getUrl());
        assertNotNull(parking.getXlong());
        assertNotNull(parking.getYlat());
        assertNotNull(parking.getCapacity());
        assertNotNull(parking.getOccupationRate());
        assertNotNull(parking.getNbOccupiedSlots());
    }

    /**
     * Allows to assert a {@link ParkingResource}
     * 
     * @param parkingResource the parking resource entity to assert
     */
    private void assertParkingResource(ParkingResource parkingResource) {
        assertNotNull(parkingResource);
        assertNotNull(parkingResource.getAddress());
        assertNotNull(parkingResource.getId());
        assertNotNull(parkingResource.getDesc());
        assertNotNull(parkingResource.getName());
        assertNotNull(parkingResource.getUrl());
        assertNotNull(parkingResource.getXlong());
        assertNotNull(parkingResource.getYlat());
        assertNotNull(parkingResource.getCapacity());
        assertNotNull(parkingResource.getOccupationRate());
        assertNotNull(parkingResource.getNbOccupiedSlots());
    }

}

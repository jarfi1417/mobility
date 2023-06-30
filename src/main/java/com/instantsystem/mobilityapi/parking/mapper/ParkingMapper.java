package com.instantsystem.mobilityapi.parking.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.instantsystem.mobilityapi.parking.entity.Parking;
import com.instantsystem.mobilityapi.parking.entity.Record;
import com.instantsystem.mobilityapi.parking.resource.ParkingResource;

/**
 * Parking mapper
 * 
 * @author jearfi
 */
@Mapper(componentModel = "spring")
public interface ParkingMapper {

    /**
     * Allows to map a {@link Parking} to a {@link ParkingResource}
     * 
     * @param parking the {@link Parking} to map
     * @return a new {@link ParkingResource}
     */
    ParkingResource asParkingResource(Parking parking);

    /**
     * Allows to map a lsit of {@link Parking} to a list of
     * {@link ParkingResource}
     * 
     * @param parkings the list of {@link Parking} to map
     * @return a new list of {@link ParkingResource}
     */
    List<ParkingResource> asParkingResourceList(List<Parking> parkings);

    /**
     * Allows to map a a list of {@link Record} to a a list of {@link Parking}
     * 
     * @param parkingRecords the a list of {@link Record} to map
     * @return a new a list of {@link Parking}
     */
    List<Parking> asParkingList(List<Record> parkingRecords);

    /**
     * Allows to map a {@link Record} to a {@link Parking}
     * 
     * @param parkingRecord the {@link Record} to map
     * @return a new {@link Parking}
     */
    @Mapping(target = "id", source = "fields.id")
    @Mapping(target = "name", source = "fields.nom")
    @Mapping(target = "address", source = "fields.adresse")
    @Mapping(target = "xlong", source = "fields.xlong")
    @Mapping(target = "ylat", source = "fields.ylat")
    @Mapping(target = "url", source = "fields.url")
    @Mapping(target = "desc", source = "fields.info")
    @Mapping(target = "nbOccupiedSlots", source = "fields.places")
    @Mapping(target = "capacity", source = "fields.capacite")
    @Mapping(target = "nbSlots", source = "fields.nbPlaces")
    @Mapping(target = "occupationRate", source = "fields.tauxOccupation")
    Parking asParking(Record parkingRecord);
}

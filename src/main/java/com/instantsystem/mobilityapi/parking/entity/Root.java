package com.instantsystem.mobilityapi.parking.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Entity that represents the root containing the list of parking records
 * 
 * @author jearfi
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {

    private List<Record> records;
}

package com.instantsystem.mobilityapi.parking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Entity that represents the a parking record data
 * 
 * @author jearfi
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Record {

    private Field fields;
}

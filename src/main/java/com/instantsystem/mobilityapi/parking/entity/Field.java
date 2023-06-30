package com.instantsystem.mobilityapi.parking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Entity that represents the fields of a parking record data
 * 
 * @author jearfi
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {

    private String url;

    private String nom;

    private double xlong;

    private String info;

    private double ylat;

    private String id;

    private String adresse;

    @JsonProperty("taux_doccupation")
    private Double tauxOccupation;

    private Long capacite;

    private Long places;

    @JsonProperty("nb_places")
    private Long nbPlaces;

}

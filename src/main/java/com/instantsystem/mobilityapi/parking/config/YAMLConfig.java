package com.instantsystem.mobilityapi.parking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * YAML Configuration class
 * 
 * @author jearfi
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class YAMLConfig {

    @Value("${app.datasource.url.parkings-list}")
    private String parkingsListUrl;

    @Value("${app.datasource.url.available-slots}")
    private String availableSlotsUrl;
}

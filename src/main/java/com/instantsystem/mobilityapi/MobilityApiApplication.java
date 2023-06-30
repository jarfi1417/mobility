package com.instantsystem.mobilityapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot main class
 * 
 * @author jearfi
 */
@SpringBootApplication
@EnableAutoConfiguration
public class MobilityApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobilityApiApplication.class, args);
    }

}

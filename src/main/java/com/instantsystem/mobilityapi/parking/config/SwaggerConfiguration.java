package com.instantsystem.mobilityapi.parking.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration class for swagger tool URL :
 * http://localhost:8888/mobilityapi/swagger-ui/
 * 
 * @author jearfi
 */
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    /**
     * the swagger API configuration bean
     * 
     * @return a new Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.instantsystem.mobilityapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Mobility REST API", //title
                "Provide services for a complete mobility experience", //description
                "Version 1.0", //version
                "Terms of service", //terms of service URL
                new Contact("Jeremy ARFI", "https://instant-system.com/", "jeremyarfi@gmail.com"),
                "License of API", "API license URL", new ArrayList<>()); // contact info
    }

}

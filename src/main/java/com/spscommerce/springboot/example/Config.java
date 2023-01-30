package com.spscommerce.springboot.example;

import com.spscommerce.shared.springboot.jaxrs.JerseyConfig;
import com.spscommerce.springboot.example.model.ExampleBean;
import com.spscommerce.springboot.example.controller.ExampleController;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Main application config
 * spring-boot uses a convention where the application.properties found in the
 * class path resources will be loaded automatically via {@link SpringBootApplication}.
 * You can override things by adding more application-profile.properties files which effectively create profiles that can be invoked
 * such as profile "prod" would be applied from application-prod.properties.
 * <p>
 * We recommend not letting configuration properties leak out of this file to avoid magic property explosion in your app!
 * Instead, create beans here that objects can use for their DI and use this as the central configuration management.
 */
@Configuration
@Import(JerseyConfig.class)
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);
    private final String testProp;
    private final String docServiceApiKey;
    private final String userServiceApiKey;

    @Autowired
    public Config(@Value("${test.prop}") String testProp, @Value("${documentService.apiKey}") String docServiceApiKey, @Value("${userService.apiKey}") String userServiceApiKey) {
        this.testProp = testProp;
        logger.info("Got our secret api key: " + docServiceApiKey);
        this.docServiceApiKey = docServiceApiKey;
        this.userServiceApiKey = userServiceApiKey;
    }

    @Bean
    public ExampleBean getExampleBean() {
        return new ExampleBean(testProp);
    }

    public String getDocServiceApiKey() {
        return docServiceApiKey;
    }

    public String getUserServiceApiKey() {
        return userServiceApiKey;
    }

    @Bean
    public ResourceConfig jersey() {
        return new ResourceConfig()
            .register(ExampleController.class);
    }
}

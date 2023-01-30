package com.spscommerce.springboot.example;

import com.spscommerce.shared.springboot.jaxrs.JerseyConfig;
import com.spscommerce.springboot.example.model.ExampleBean;
import com.spscommerce.springboot.example.resources.ExampleResource;
import org.glassfish.jersey.server.ResourceConfig;
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

    private final String testProp;

    @Autowired
    public Config(@Value("${test.prop}") String testProp) {
        this.testProp = testProp;
    }

    @Bean
    public ExampleBean getExampleBean() {
        return new ExampleBean(testProp);
    }

    @Bean
    public ResourceConfig jersey() {
        return new ResourceConfig()
            .register(ExampleResource.class);
    }
}

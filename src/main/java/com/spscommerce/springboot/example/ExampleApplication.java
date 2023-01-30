package com.spscommerce.springboot.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@link SpringBootApplication} will scan all packages under its own package, in
 * this case "com.spscommerce.springboot.example.*" and also any auto-configuration
 * packages on the classpath (sps-java-shared-spring-boot-core includes some, like actuator)
 */
@SpringBootApplication
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}

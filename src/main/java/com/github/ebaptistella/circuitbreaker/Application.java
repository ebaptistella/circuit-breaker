package com.github.ebaptistella.circuitbreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = { "com.github.ebaptistella.circuitbreaker" })
@PropertySource(ignoreResourceNotFound = true, value = "classpath:cb-custom-${spring.profiles.active}.properties")
@PropertySource(ignoreResourceNotFound = false, value = "classpath:application-${spring.profiles.active}.properties")
@PropertySource(ignoreResourceNotFound = false, value = "classpath:application.properties")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}

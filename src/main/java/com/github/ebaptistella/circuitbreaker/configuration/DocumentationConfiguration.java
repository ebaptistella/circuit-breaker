package com.github.ebaptistella.circuitbreaker.configuration;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.BASIC_AUTH;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerPackageConstants.PKG_API;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.github.ebaptistella.circuitbreaker.util.SwaggerConfigurationUtil;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class DocumentationConfiguration {

    @Value("${application.version}")
    private String version;

    @Bean
    @Lazy
    public Docket swaggerConfiguration() {

        return new SwaggerConfigurationUtil(PKG_API,
                new ApiInfo("REST API - Circuit Breaker", "Documentação da API", this.version, "Terms of service",
                        new Contact("Eurides Baptistella", "https://github.com/ebaptistella/circuit-breaker", "eurides.baptistella@gmail.com"),
                        Strings.EMPTY, Strings.EMPTY, new ArrayList<>())).produces().groupName("Circuit Breaker")
                                .securitySchemes(Arrays.asList(new BasicAuth(BASIC_AUTH)));
    }

}

package com.github.ebaptistella.circuitbreaker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ebaptistella.circuitbreaker.util.ControllerInterceptorLoggerUtil;

@Configuration
public class InterceptorControllerConfiguration {

    @Bean
    public ControllerInterceptorLoggerUtil controllerInterceptor() {
	return new ControllerInterceptorLoggerUtil();
    }

}

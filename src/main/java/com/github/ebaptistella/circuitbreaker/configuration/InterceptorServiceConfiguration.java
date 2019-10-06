package com.github.ebaptistella.circuitbreaker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ebaptistella.circuitbreaker.util.ServiceInterceptorLoggerUtil;

@Configuration
public class InterceptorServiceConfiguration {

    @Bean
    public ServiceInterceptorLoggerUtil serviceInterceptor() {
	return new ServiceInterceptorLoggerUtil();
    }

}

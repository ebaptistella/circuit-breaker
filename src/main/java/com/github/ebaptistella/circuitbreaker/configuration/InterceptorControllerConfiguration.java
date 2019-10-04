package com.github.ebaptistella.circuitbreaker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.github.ebaptistella.circuitbreaker.util.ControllerInterceptorLoggerUtil;

@Configuration
@EnableAspectJAutoProxy
public class InterceptorControllerConfiguration {

    @Bean
    public ControllerInterceptorLoggerUtil controllerInterceptor() {
        return new ControllerInterceptorLoggerUtil();
    }

}

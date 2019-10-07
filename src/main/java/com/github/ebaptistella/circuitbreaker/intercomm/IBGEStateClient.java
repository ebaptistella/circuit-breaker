package com.github.ebaptistella.circuitbreaker.intercomm;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.STATE_CLIENT_NAME;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.CLIENT_URL;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.URL_REQUEST_ALL_STATES;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.ebaptistella.circuitbreaker.configuration.FeignConfiguration;
import com.github.ebaptistella.circuitbreaker.dto.UFDTO;
import com.github.ebaptistella.circuitbreaker.service.impl.IBGEStateFallbackServiceImpl;

@FeignClient(name = STATE_CLIENT_NAME, url = CLIENT_URL, configuration = FeignConfiguration.class, fallback = IBGEStateFallbackServiceImpl.class)
public interface IBGEStateClient {

    @GetMapping(value = URL_REQUEST_ALL_STATES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract List<UFDTO> getAll();
}

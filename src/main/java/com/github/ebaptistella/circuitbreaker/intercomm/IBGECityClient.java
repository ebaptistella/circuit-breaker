package com.github.ebaptistella.circuitbreaker.intercomm;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PRM_UF;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.CITY_CLIENT_NAME;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.CLIENT_URL;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.URL_REQUEST_ALL_CITIES;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.URL_REQUEST_CITY_BY_STATE;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.ebaptistella.circuitbreaker.configuration.FeignConfiguration;
import com.github.ebaptistella.circuitbreaker.dto.MunicipioDTO;
import com.github.ebaptistella.circuitbreaker.service.impl.IBGECityFallbackServiceImpl;

@FeignClient(name = CITY_CLIENT_NAME, url = CLIENT_URL, configuration = FeignConfiguration.class, fallback = IBGECityFallbackServiceImpl.class)
public interface IBGECityClient {

    @GetMapping(value = URL_REQUEST_CITY_BY_STATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract List<MunicipioDTO> findByState(@PathVariable(value = PRM_UF, required = true) String uf);

    @GetMapping(value = URL_REQUEST_ALL_CITIES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract List<MunicipioDTO> getAll();

}

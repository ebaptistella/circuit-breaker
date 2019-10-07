package com.github.ebaptistella.circuitbreaker.service.impl;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.CLIENT_URL;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.URL_REQUEST_ALL_CITIES;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.URL_REQUEST_CITY_BY_STATE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.ebaptistella.circuitbreaker.dto.MunicipioRetornoDTO;
import com.github.ebaptistella.circuitbreaker.intercomm.IBGECityClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IBGECityFallbackServiceImpl implements IBGECityClient {

    @Override
    public List<MunicipioRetornoDTO> findByState(String uf) {
	log.error("==>Erro ao acessar o webservice: {} path: {}", CLIENT_URL, URL_REQUEST_CITY_BY_STATE);
	return new ArrayList<>();
    }

    @Override
    public List<MunicipioRetornoDTO> getAll() {
	log.error("==>Erro ao acessar o webservice: {}  path: {}", CLIENT_URL, URL_REQUEST_ALL_CITIES);
	return new ArrayList<>();
    }

}

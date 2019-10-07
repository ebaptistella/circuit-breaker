package com.github.ebaptistella.circuitbreaker.service.impl;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerFeignClientConstants.CLIENT_URL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.ebaptistella.circuitbreaker.dto.UFDTO;
import com.github.ebaptistella.circuitbreaker.intercomm.IBGEStateClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IBGEStateFallbackServiceImpl implements IBGEStateClient {

    @Override
    public List<UFDTO> getAll() {
	log.error("==>Erro ao acessar o webservice: {}", CLIENT_URL);
	return new ArrayList<>();
    }
}

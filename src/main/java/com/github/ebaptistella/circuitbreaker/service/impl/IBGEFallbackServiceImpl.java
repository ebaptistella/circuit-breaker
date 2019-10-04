package com.github.ebaptistella.circuitbreaker.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.ebaptistella.circuitbreaker.dto.UFDTO;
import com.github.ebaptistella.circuitbreaker.intercomm.IBGEClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IBGEFallbackServiceImpl implements IBGEClient {

    @Override
    public List<UFDTO> getAll() {
        log.error("testes");

        return new ArrayList<>();
    }

}

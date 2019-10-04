package com.github.ebaptistella.circuitbreaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.github.ebaptistella.circuitbreaker.dto.UFDTO;
import com.github.ebaptistella.circuitbreaker.intercomm.IBGEClient;
import com.github.ebaptistella.circuitbreaker.service.StateService;

@Service
public class StateServiceImpl implements StateService {

    @Lazy
    @Autowired
    private IBGEClient ibgeClient;

    @Override
    public List<UFDTO> getAll() {
        return ibgeClient.getAll();
    }

}

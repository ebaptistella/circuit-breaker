package com.github.ebaptistella.circuitbreaker.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.github.ebaptistella.circuitbreaker.api.StateController;
import com.github.ebaptistella.circuitbreaker.dto.UFDTO;
import com.github.ebaptistella.circuitbreaker.service.StateService;

@RestController
public class StateControllerImpl implements StateController {

    @Autowired
    private StateService stateService;

    @Override
    public ResponseEntity<List<UFDTO>> getAll() {
        return ResponseEntity.ok(stateService.getAll());
    }

}

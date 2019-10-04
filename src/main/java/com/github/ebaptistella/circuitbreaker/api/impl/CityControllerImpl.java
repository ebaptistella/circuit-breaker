package com.github.ebaptistella.circuitbreaker.api.impl;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PRM_CITY_NAME;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.ebaptistella.circuitbreaker.api.CityController;
import com.github.ebaptistella.circuitbreaker.dto.MunicipioDTO;

@RestController
public class CityControllerImpl implements CityController {

    @Override
    public ResponseEntity<List<MunicipioDTO>> getAll() {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<MunicipioDTO>> findByName(@PathVariable(value = PRM_CITY_NAME, required = true) String cityName) {
        return ResponseEntity.ok().build();
    }

}

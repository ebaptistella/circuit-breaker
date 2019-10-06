package com.github.ebaptistella.circuitbreaker.api.impl;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PRM_CITY_NAME;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PRM_STATE_CODE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.ebaptistella.circuitbreaker.api.CityController;
import com.github.ebaptistella.circuitbreaker.dto.MunicipioDTO;
import com.github.ebaptistella.circuitbreaker.service.CityService;

@RestController
public class CityControllerImpl implements CityController {

    @Lazy
    @Autowired
    private CityService cityService;

    @Autowired
    @Lazy
    private ApplicationContext context;

    @Override
    public ResponseEntity<List<MunicipioDTO>> getAll() {
	printCache("city");
	return ResponseEntity.ok(cityService.getAll());
    }

    @Override
    public ResponseEntity<List<MunicipioDTO>> findByState(
	    @PathVariable(value = PRM_STATE_CODE, required = true) String stateCode) {
	printCache("city");
	return ResponseEntity.ok(cityService.findByState(stateCode));
    }

    @Override
    public ResponseEntity<Long> findByName(@PathVariable(value = PRM_CITY_NAME, required = true) String cityName) {
	return ResponseEntity.ok(cityService.findByName(cityName));
    }

    @Override
    public ResponseEntity<Void> clearCache() {
	cityService.clearCache();
	return ResponseEntity.ok().build();
    }

    private void printCache(String cache) {
	CacheManager cm = context.getBean(CacheManager.class);
	Cache oCache = cm.getCache(cache);
	System.out.println(oCache.getNativeCache());
    }

}

package com.github.ebaptistella.circuitbreaker.api.impl;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PRM_CITY_NAME;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PRM_STATE_CODE;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.ebaptistella.circuitbreaker.api.CityController;
import com.github.ebaptistella.circuitbreaker.dto.MunicipioRetornoDTO;
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
    public ResponseEntity<List<MunicipioRetornoDTO>> getAll() {
	return ResponseEntity.ok(cityService.getAll());
    }

    @Override
    public ResponseEntity<List<MunicipioRetornoDTO>> findByState(
	    @PathVariable(value = PRM_STATE_CODE, required = true) String stateCode) {
	return ResponseEntity.ok(cityService.findByState(stateCode));
    }

    @Override
    public ResponseEntity<Long> findByName(@PathVariable(value = PRM_CITY_NAME, required = true) String cityName) {
	return ResponseEntity.ok(cityService.findByName(cityName));
    }

    @Override
    public ResponseEntity<Void> clearCache() {
	return ResponseEntity.ok().build();
    }

    @Override
    public void download(HttpServletResponse response) throws IOException {

	response.setCharacterEncoding("ISO-8859-1");
	response.setHeader("Content-Disposition", "attachment; filename=city-report.csv");
	response.setContentType("application/octet-stream");

	cityService.generateReportFile(response.getWriter());

	response.flushBuffer();
    }

}

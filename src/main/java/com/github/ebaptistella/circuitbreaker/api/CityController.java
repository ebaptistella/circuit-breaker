package com.github.ebaptistella.circuitbreaker.api;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.CITY_REQUEST_MAPPING;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PRM_CITY_NAME;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PRM_STATE_CODE;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PROTOCOLO_HTTP;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_200;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_204;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_401;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_403;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_404;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_500;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.BASIC_AUTH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.ebaptistella.circuitbreaker.dto.MunicipioDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(tags = { "Endpoint das cidades do Brasil" }, protocols = PROTOCOLO_HTTP)
@RequestMapping(value = CITY_REQUEST_MAPPING, consumes = { APPLICATION_JSON_VALUE }, produces = {
	APPLICATION_JSON_VALUE })
public interface CityController {

    @ApiOperation(value = "Adquirir lista de todas as cidades do Brasil", response = MunicipioDTO.class, authorizations = {
	    @Authorization(value = BASIC_AUTH) })
    @ApiResponses(value = { @ApiResponse(code = 200, message = STATUS_CODE_200),
	    @ApiResponse(code = 401, message = STATUS_CODE_401), @ApiResponse(code = 403, message = STATUS_CODE_403),
	    @ApiResponse(code = 404, message = STATUS_CODE_404), @ApiResponse(code = 500, message = STATUS_CODE_500) })
    @GetMapping
    public abstract ResponseEntity<List<MunicipioDTO>> getAll();

    @ApiOperation(value = "Adquirir lista de todas as cidades de um estado do Brasil", response = MunicipioDTO.class, authorizations = {
	    @Authorization(value = BASIC_AUTH) })
    @ApiResponses(value = { @ApiResponse(code = 200, message = STATUS_CODE_200),
	    @ApiResponse(code = 401, message = STATUS_CODE_401), @ApiResponse(code = 403, message = STATUS_CODE_403),
	    @ApiResponse(code = 404, message = STATUS_CODE_404), @ApiResponse(code = 500, message = STATUS_CODE_500) })
    @GetMapping("uf/{stateCode}")
    public abstract ResponseEntity<List<MunicipioDTO>> findByState(
	    @ApiParam(value = PRM_STATE_CODE, required = true) String stateCode);

    @ApiOperation(value = "Adquirir lista de cidades do Brasil filtrando por nome", response = Long.class, authorizations = {
	    @Authorization(value = BASIC_AUTH) })
    @ApiResponses(value = { @ApiResponse(code = 200, message = STATUS_CODE_200),
	    @ApiResponse(code = 401, message = STATUS_CODE_401), @ApiResponse(code = 403, message = STATUS_CODE_403),
	    @ApiResponse(code = 404, message = STATUS_CODE_404), @ApiResponse(code = 500, message = STATUS_CODE_500) })
    @GetMapping("{cityName}")
    public abstract ResponseEntity<Long> findByName(@ApiParam(value = PRM_CITY_NAME, required = true) String cityName);

    @ApiOperation(value = "Limpar cache de cidades do Brasil", response = Void.class, authorizations = {
	    @Authorization(value = BASIC_AUTH) })
    @ApiResponses(value = { @ApiResponse(code = 200, message = STATUS_CODE_200),
	    @ApiResponse(code = 204, message = STATUS_CODE_204), @ApiResponse(code = 401, message = STATUS_CODE_401),
	    @ApiResponse(code = 403, message = STATUS_CODE_403), @ApiResponse(code = 500, message = STATUS_CODE_500) })
    @DeleteMapping
    public abstract ResponseEntity<Void> clearCache();
}

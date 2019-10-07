package com.github.ebaptistella.circuitbreaker.api;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.PROTOCOLO_HTTP;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATE_REQUEST_MAPPING;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_200;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_204;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_401;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_403;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_404;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_406;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATUS_CODE_500;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.BASIC_AUTH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.ebaptistella.circuitbreaker.dto.UFDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(tags = { "Endpoint estados do Brasil" }, protocols = PROTOCOLO_HTTP)
@RequestMapping(value = STATE_REQUEST_MAPPING, consumes = { APPLICATION_JSON_VALUE }, produces = {
	APPLICATION_JSON_VALUE })
public interface StateController {

    @ApiOperation(value = "Adquirir lista de todos os estados do Brasil", response = UFDTO.class, authorizations = {
	    @Authorization(value = BASIC_AUTH) })
    @ApiResponses(value = { @ApiResponse(code = 200, message = STATUS_CODE_200),
	    @ApiResponse(code = 401, message = STATUS_CODE_401), @ApiResponse(code = 403, message = STATUS_CODE_403),
	    @ApiResponse(code = 404, message = STATUS_CODE_404), @ApiResponse(code = 500, message = STATUS_CODE_500) })
    @GetMapping
    public abstract ResponseEntity<List<UFDTO>> getAll();

    @ApiOperation(value = "Gera lista de todos os estados do Brasil no formato especificado", response = Void.class, authorizations = {
	    @Authorization(value = BASIC_AUTH) })
    @ApiResponses(value = { @ApiResponse(code = 200, message = STATUS_CODE_200),
	    @ApiResponse(code = 401, message = STATUS_CODE_401), @ApiResponse(code = 403, message = STATUS_CODE_403),
	    @ApiResponse(code = 404, message = STATUS_CODE_404), @ApiResponse(code = 406, message = STATUS_CODE_406),
	    @ApiResponse(code = 500, message = STATUS_CODE_500) })
    @GetMapping(path = "/download", produces = APPLICATION_OCTET_STREAM_VALUE)
    public abstract ResponseEntity<Void> download(HttpServletResponse response) throws IOException;

    @ApiOperation(value = "Limpar cache de estados do Brasil", response = Void.class, authorizations = {
	    @Authorization(value = BASIC_AUTH) })
    @ApiResponses(value = { @ApiResponse(code = 200, message = STATUS_CODE_200),
	    @ApiResponse(code = 204, message = STATUS_CODE_204), @ApiResponse(code = 401, message = STATUS_CODE_401),
	    @ApiResponse(code = 403, message = STATUS_CODE_403) })
    @DeleteMapping
    public abstract ResponseEntity<Void> clearCache();
}

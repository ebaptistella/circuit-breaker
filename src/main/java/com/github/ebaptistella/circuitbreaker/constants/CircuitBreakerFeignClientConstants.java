package com.github.ebaptistella.circuitbreaker.constants;

public class CircuitBreakerFeignClientConstants {

    public static final String CLIENT_NAME = "IBGEClient";
    public static final String CLIENT_URL = "https://servicodados.ibge.gov.br";
    public static final String URL_REQUEST_ALL_STATES = "api/v1/localidades/estados";

    private CircuitBreakerFeignClientConstants() {
    }

}

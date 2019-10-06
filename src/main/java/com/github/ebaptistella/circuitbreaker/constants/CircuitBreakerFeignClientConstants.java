package com.github.ebaptistella.circuitbreaker.constants;

public class CircuitBreakerFeignClientConstants {

    public static final String STATE_CLIENT_NAME = "IBGEStateClient";
    public static final String CITY_CLIENT_NAME = "IBGECityClient";
    public static final String CLIENT_URL = "https://servicodados.ibge.gov.br";
    public static final String URL_REQUEST_ALL_STATES = "api/v1/localidades/estados";
    public static final String URL_REQUEST_ALL_CITIES = "api/v1/localidades/municipios";
    public static final String URL_REQUEST_CITY_BY_STATE = "api/v1/localidades/estados/{UF}/municipios";

    private CircuitBreakerFeignClientConstants() {
    }

}

package com.github.ebaptistella.circuitbreaker.constants;

public class CircuitBreakerAPIConstants {

    public static final String PROTOCOLO_HTTP = "http";

    public static final String STATUS_CODE_200 = "Registro encontrado.";
    public static final String STATUS_CODE_201 = "Registro criado";
    public static final String STATUS_CODE_204 = "Nenhum registro encontrado.";
    public static final String STATUS_CODE_400 = "Requisição falha (bad request).";
    public static final String STATUS_CODE_401 = "Sem permissão (Unauthorized).";
    public static final String STATUS_CODE_403 = "Proibido (Forbidden).";
    public static final String STATUS_CODE_404 = "Não encontrado (Not Found).";
    public static final String STATUS_CODE_500 = "Erro interno (Internal Server Error).";

    public static final String STATE_REQUEST_MAPPING = "state";
    public static final String CITY_REQUEST_MAPPING = "city";

    public static final String PRM_CITY_NAME = "cityName";

    private CircuitBreakerAPIConstants() {
    }

}

package com.github.ebaptistella.circuitbreaker.service;

import java.util.List;

import com.github.ebaptistella.circuitbreaker.dto.MunicipioRetornoDTO;

public interface CityService {

    public abstract List<MunicipioRetornoDTO> getAll();

    public abstract List<MunicipioRetornoDTO> findByState(String stateCode);

    public abstract Long findByName(String cityName);

    public abstract void clearCache();

}

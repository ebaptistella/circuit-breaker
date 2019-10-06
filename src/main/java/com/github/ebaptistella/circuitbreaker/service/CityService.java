package com.github.ebaptistella.circuitbreaker.service;

import java.util.List;

import com.github.ebaptistella.circuitbreaker.dto.MunicipioDTO;

public interface CityService {

    public abstract List<MunicipioDTO> getAll();

    public abstract List<MunicipioDTO> findByState(String stateCode);

    public abstract Long findByName(String cityName);

    public abstract void clearCache();

}

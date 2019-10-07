package com.github.ebaptistella.circuitbreaker.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.github.ebaptistella.circuitbreaker.dto.MunicipioRetornoDTO;

public interface CityService {

    public abstract List<MunicipioRetornoDTO> getAll();

    public abstract List<MunicipioRetornoDTO> findByState(String stateCode);

    public abstract Long findByName(String cityName);

    public abstract void clearCache();

    public abstract InputStream generateReportFile() throws IOException;

}

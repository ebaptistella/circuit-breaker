package com.github.ebaptistella.circuitbreaker.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.github.ebaptistella.circuitbreaker.dto.MunicipioDTO;
import com.github.ebaptistella.circuitbreaker.intercomm.IBGECityClient;
import com.github.ebaptistella.circuitbreaker.service.CityService;

@Service
@CacheConfig(cacheNames = "city")
public class CityServiceImpl implements CityService {

    @Lazy
    @Autowired
    private IBGECityClient cityClient;

    @Override
    @Cacheable(unless = "#result == null or #result.size() == 0")
    public List<MunicipioDTO> getAll() {
	return cityClient.getAll();
    }

    @Override
    @Cacheable(key = "#stateCode", unless = "#result == null or #result.size() == 0")
    public List<MunicipioDTO> findByState(String stateCode) {
	return cityClient.findByState(stateCode);
    }

    @Override
    @Cacheable(key = "#cityName", unless = "#result == null")
    public Long findByName(String cityName) {
	List<MunicipioDTO> municipioList = this.getAll();

	Optional<MunicipioDTO> municipioDTO = municipioList.parallelStream()
		.filter(municipio -> cityName.equals(municipio.getNome())).findFirst();

	if (municipioDTO.isPresent()) {
	    return municipioDTO.get().getId();
	}

	return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void clearCache() {
	return;
    }
}

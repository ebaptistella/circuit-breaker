package com.github.ebaptistella.circuitbreaker.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.github.ebaptistella.circuitbreaker.dto.MunicipioRetornoDTO;
import com.github.ebaptistella.circuitbreaker.enumerator.WriteFileToEnum;
import com.github.ebaptistella.circuitbreaker.factory.WriteToFileFactory;
import com.github.ebaptistella.circuitbreaker.intercomm.IBGECityClient;
import com.github.ebaptistella.circuitbreaker.service.CityService;

@Service
@CacheConfig(cacheNames = "city")
public class CityServiceImpl implements CityService {

    @Lazy
    @Autowired
    private IBGECityClient cityClient;

    @Lazy
    @Autowired
    private WriteToFileFactory writeToFileFactory;

    @Override
    @Cacheable(unless = "#result == null or #result.size() == 0")
    public List<MunicipioRetornoDTO> getAll() {
	return cityClient.getAll();
    }

    @Override
    @Cacheable(key = "#stateCode", unless = "#result == null or #result.size() == 0")
    public List<MunicipioRetornoDTO> findByState(String stateCode) {
	return cityClient.findByState(stateCode);
    }

    @Override
    @Cacheable(key = "#cityName", unless = "#result == null")
    public Long findByName(String cityName) {
	List<MunicipioRetornoDTO> municipioList = cityClient.getAll();

	Optional<MunicipioRetornoDTO> municipioDTO = municipioList.parallelStream()
		.filter(municipio -> cityName.equals(municipio.getNomeCidade())).findFirst();

	if (municipioDTO.isPresent()) {
	    return municipioDTO.get().getCodigoCidade();
	}

	return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void clearCache() {
	return;
    }

    @Override
    public InputStream generateReportFile() throws IOException {
	List<MunicipioRetornoDTO> municipioRetornoDTOList = this.getAll();
	writeToFileFactory.create(WriteFileToEnum.WF_CSV).write("city-report.csv", municipioRetornoDTOList);

	return new FileInputStream(new File("city-report.csv"));
    }
}

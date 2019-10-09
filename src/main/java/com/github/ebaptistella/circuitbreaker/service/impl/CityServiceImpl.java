package com.github.ebaptistella.circuitbreaker.service.impl;

import java.io.PrintWriter;
import java.lang.reflect.Field;
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
import com.github.ebaptistella.circuitbreaker.output.service.WriteFileTo;
import com.github.ebaptistella.circuitbreaker.service.CityService;
import com.github.ebaptistella.circuitbreaker.util.ReflectionUtil;

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
	List<MunicipioRetornoDTO> municipioList = this.getAll();

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
    public void generateReportFile(PrintWriter printerWriter) throws Exception {

	List<MunicipioRetornoDTO> municipioRetornoDTOList = this.getAll();

	if (municipioRetornoDTOList.isEmpty()) {
	    throw new Exception("Lista de municípios encontra-se vazia.");
	}

	WriteFileTo csv = writeToFileFactory.create(WriteFileToEnum.WF_CSV, printerWriter);

	String[] excludeFieldNames = new String[] { "serialVersionUID", "codigoCidade" };
	Field[] fields = ReflectionUtil.getDeclaredFields(MunicipioRetornoDTO.class, excludeFieldNames);

	csv.write(ReflectionUtil.getFieldNames(fields));

	municipioRetornoDTOList.stream()
		.forEach(municipio -> csv.write(ReflectionUtil.getDeclaredFieldsValues(municipio, excludeFieldNames)));

    }

}

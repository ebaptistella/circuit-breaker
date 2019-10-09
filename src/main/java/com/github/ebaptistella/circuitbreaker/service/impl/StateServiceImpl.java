package com.github.ebaptistella.circuitbreaker.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.github.ebaptistella.circuitbreaker.dto.UFDTO;
import com.github.ebaptistella.circuitbreaker.factory.WriteToFileFactory;
import com.github.ebaptistella.circuitbreaker.intercomm.IBGEStateClient;
import com.github.ebaptistella.circuitbreaker.service.StateService;
import com.github.ebaptistella.circuitbreaker.util.ReflectionUtil;

@Service
@CacheConfig(cacheNames = "state")
public class StateServiceImpl implements StateService {

    @Lazy
    @Autowired
    private IBGEStateClient stateClient;

    @Lazy
    @Autowired
    private WriteToFileFactory writeToFileFactory;

    @Override
    @Cacheable
    public List<UFDTO> getAll() {
	return stateClient.getAll();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void clearCache() {
	return;
    }

    @Override
    public void generateReportFile(PrintWriter printerWriter) throws IOException {
	List<UFDTO> ufDTOList = this.getAll();

	String[] excludeFieldNames = new String[] { "serialVersionUID", "regiao" };
	Field[] fields = ReflectionUtil.getDeclaredFields(UFDTO.class, excludeFieldNames);

	printerWriter.write(String.join(",", ReflectionUtil.getFieldNames(fields)));
	printerWriter.write("\n");

	ufDTOList.stream().forEach(uf -> {
	    printerWriter.write(String.join(",", ReflectionUtil.getDeclaredFieldsValues(uf, excludeFieldNames)));
	    printerWriter.write("\n");
	});

    }

}

package com.github.ebaptistella.circuitbreaker.service.impl;

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
import com.github.ebaptistella.circuitbreaker.enumerator.WriteFileToEnum;
import com.github.ebaptistella.circuitbreaker.factory.WriteToFileFactory;
import com.github.ebaptistella.circuitbreaker.intercomm.IBGEStateClient;
import com.github.ebaptistella.circuitbreaker.output.service.WriteFileTo;
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
    public void generateReportFile(PrintWriter printerWriter) throws Exception {
	List<UFDTO> ufDTOList = this.getAll();

	if (ufDTOList.isEmpty()) {
	    throw new Exception("Lista de estados encontra-se vazia.");
	}

	WriteFileTo csv = writeToFileFactory.create(WriteFileToEnum.WF_CSV, printerWriter);

	String[] excludeFieldNames = new String[] { "serialVersionUID", "regiao" };
	Field[] fields = ReflectionUtil.getDeclaredFields(UFDTO.class, excludeFieldNames);

	csv.write(ReflectionUtil.getFieldNames(fields));

	ufDTOList.stream().forEach(uf -> csv.write(ReflectionUtil.getDeclaredFieldsValues(uf, excludeFieldNames)));

    }

}

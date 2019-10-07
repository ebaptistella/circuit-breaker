package com.github.ebaptistella.circuitbreaker.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import com.github.ebaptistella.circuitbreaker.service.StateService;
import com.opencsv.CSVWriter;

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
    public InputStream generateReportFile() throws IOException {
        List<UFDTO> ufDTOList = this.getAll();
        writeToFileFactory.create(WriteFileToEnum.WF_CSV).write("state-report.csv", ufDTOList);

        return new FileInputStream(new File("state-report.csv"));
    }

    @Override
    public void generateReportFile2(PrintWriter printerWriter) throws IOException {

        List<UFDTO> ufDTOList = this.getAll();

        CSVWriter w = new CSVWriter(printerWriter);

        List<String> headerList = this.getHeader(UFDTO.class);
        String[] headerArray = new String[headerList.size()];
        headerList.toArray(headerArray);

        w.writeNext(headerList.toArray(headerArray));

        ufDTOList.stream().forEach(uf -> w.writeNext(new String[] { uf.getId().toString(), uf.getNome(), uf.getSigla() }));

        w.flush();
        w.close();

    }

    private <T> List<String> getHeader(Class<T> clazz) {

        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldList = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
        fieldList.remove("serialVersionUID");

        return fieldList;
    }
}

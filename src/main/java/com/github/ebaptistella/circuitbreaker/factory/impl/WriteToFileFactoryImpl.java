package com.github.ebaptistella.circuitbreaker.factory.impl;

import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.github.ebaptistella.circuitbreaker.enumerator.WriteFileToEnum;
import com.github.ebaptistella.circuitbreaker.factory.WriteToFileFactory;
import com.github.ebaptistella.circuitbreaker.output.service.WriteFileTo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WriteToFileFactoryImpl implements WriteToFileFactory {

    @Lazy
    @Qualifier("CSV")
    @Autowired
    private WriteFileTo writeToCSV;

    @Override
    public WriteFileTo create(WriteFileToEnum writeFile, Writer writer) {
	switch (writeFile) {
	case WF_CSV:
	    this.writeToCSV.setWriter(writer);
	    return this.writeToCSV;

	default:
	    log.error("==>Não identificado o formato para escrita");
	    return null;
	}
    }

}

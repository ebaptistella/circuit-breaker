package com.github.ebaptistella.circuitbreaker.output.service.impl;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.ebaptistella.circuitbreaker.output.service.WriteFileTo;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import lombok.extern.slf4j.Slf4j;

@Service("CSV")
@Slf4j
public class WriteFileToCSVImpl implements WriteFileTo {

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> void write(String file, List<T> list) {
	try (Writer writer = Files.newBufferedWriter(Paths.get(file));) {
	    StatefulBeanToCsv<T> csv = new StatefulBeanToCsvBuilder(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
		    .build();

	    csv.write(list);
	} catch (Exception e) {
	    log.error("==>Erro ao salvar arquivo.", e);
	}
    }

}

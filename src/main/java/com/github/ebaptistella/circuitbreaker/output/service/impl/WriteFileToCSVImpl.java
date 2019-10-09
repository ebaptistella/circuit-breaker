package com.github.ebaptistella.circuitbreaker.output.service.impl;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Service;

import com.github.ebaptistella.circuitbreaker.output.service.WriteFileTo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service("CSV")
@Slf4j
public class WriteFileToCSVImpl implements WriteFileTo {

    @Getter
    @Setter
    private Writer writer;

    @Override
    public void write(String[] text) {
	try {
	    this.writer.write(String.join(",", text));
	    this.writer.write("\n");
	} catch (IOException e) {
	    log.error("==>Erro ao escrever texto em CSV", e);
	}
    }

}

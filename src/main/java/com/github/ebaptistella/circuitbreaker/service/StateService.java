package com.github.ebaptistella.circuitbreaker.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.github.ebaptistella.circuitbreaker.dto.UFDTO;

public interface StateService {

    public abstract List<UFDTO> getAll();

    public abstract void clearCache();

    public abstract void generateReportFile(PrintWriter printerWriter) throws IOException;

}

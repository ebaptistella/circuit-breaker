package com.github.ebaptistella.circuitbreaker.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.github.ebaptistella.circuitbreaker.api.StateController;
import com.github.ebaptistella.circuitbreaker.dto.UFDTO;
import com.github.ebaptistella.circuitbreaker.service.StateService;

@RestController
public class StateControllerImpl implements StateController {

    @Lazy
    @Autowired
    private StateService stateService;

    @Override
    public ResponseEntity<List<UFDTO>> getAll() {
	return ResponseEntity.ok(stateService.getAll());
    }

    @Override
    public ResponseEntity<Void> clearCache() {
	stateService.clearCache();
	return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> download(HttpServletResponse response) throws IOException {

	InputStream report = stateService.generateReportFile();
	IOUtils.copy(report, response.getOutputStream());

	response.addHeader("Content-Disposition", "attachment;filename=state-report.csv");
	response.setContentType("txt/plain");

	response.flushBuffer();
	report.close();

	return ResponseEntity.ok().build();
    }

}

package com.github.ebaptistella.circuitbreaker.service;

import java.util.List;

import com.github.ebaptistella.circuitbreaker.dto.UFDTO;

public interface StateService {

    public abstract List<UFDTO> getAll();

    public abstract void clearCache();

}

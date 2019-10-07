package com.github.ebaptistella.circuitbreaker.output.service;

import java.util.List;

public interface WriteFileTo {

    public <T> void write(String file, List<T> list);

}

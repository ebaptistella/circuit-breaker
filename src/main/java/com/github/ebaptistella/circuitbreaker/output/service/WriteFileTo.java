package com.github.ebaptistella.circuitbreaker.output.service;

import java.io.Writer;

public interface WriteFileTo {

    public void write(String[] text);

    public void setWriter(Writer writer);

}

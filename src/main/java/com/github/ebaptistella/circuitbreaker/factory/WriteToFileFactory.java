package com.github.ebaptistella.circuitbreaker.factory;

import java.io.Writer;

import com.github.ebaptistella.circuitbreaker.enumerator.WriteFileToEnum;
import com.github.ebaptistella.circuitbreaker.output.service.WriteFileTo;

public interface WriteToFileFactory {

    public abstract WriteFileTo create(WriteFileToEnum writeFile, Writer writer);
}

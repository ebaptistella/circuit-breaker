package com.github.ebaptistella.circuitbreaker.enumerator;

public enum WriteFileToEnum {

    WF_CSV(1L), WF_PDF(2L);

    private final Long ext;

    WriteFileToEnum(Long ext) {
	this.ext = ext;
    }

    public Long getExt() {
	return ext;
    }

}

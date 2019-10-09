package com.github.ebaptistella.circuitbreaker.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {

    private static final Logger log = LoggerFactory.getLogger(ReflectionUtil.class);

    private ReflectionUtil() {
    }

    public static <T> List<String> getDeclaredFieldsToStringList(Class<T> clazz, String[] excludes) {

	Field[] fields = clazz.getDeclaredFields();
	List<String> fieldList = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());

	for (String field : excludes) {
	    fieldList.remove(field);
	}

	return fieldList;
    }

    public static <T> Field[] getDeclaredFields(Class<T> clazz, String[] excludes) {
	List<Field> fieldList = new ArrayList<>();

	Field[] fields = clazz.getDeclaredFields();
	Arrays.stream(fields).forEach(f -> {
	    Boolean equals = Boolean.FALSE;

	    for (String field : excludes) {
		if (field.equals(f.getName())) {
		    equals = Boolean.TRUE;
		    break;
		}
	    }

	    if (!equals) {
		fieldList.add(f);
	    }
	});

	Field[] fieldArray = new Field[fieldList.size()];
	fieldList.toArray(fieldArray);

	return fieldArray;
    }

    public static <T> String[] getDeclaredFieldsValues(T obj, String[] excludes) {

	Field[] fields = obj.getClass().getDeclaredFields();

	List<String> fieldList = new ArrayList<>();
	Arrays.stream(fields).forEach(f -> {
	    Boolean equals = Boolean.FALSE;

	    for (String field : excludes) {
		if (field.equals(f.getName())) {
		    equals = Boolean.TRUE;
		    break;
		}
	    }

	    if (!equals) {
		try {
		    f.setAccessible(true);
		    if (Long.class.isAssignableFrom(f.getType())) {
			fieldList.add(String.valueOf(f.get(obj)));
		    } else {
			fieldList.add((String) f.get(obj));
		    }

		} catch (Exception e) {
		    log.error("==>Erro ao buscar valor para o field: {}", f.getName(), e);
		}

	    }
	});

	String[] fieldArray = new String[fieldList.size()];
	fieldList.toArray(fieldArray);

	return fieldArray;
    }

    public static String[] getFieldNames(Field[] fields) {
	List<String> fieldList = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());

	String[] fieldNames = new String[fieldList.size()];
	fieldList.toArray(fieldNames);

	return fieldNames;
    }
}

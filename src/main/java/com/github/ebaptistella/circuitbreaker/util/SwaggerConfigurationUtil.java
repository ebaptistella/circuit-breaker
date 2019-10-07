package com.github.ebaptistella.circuitbreaker.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfigurationUtil {

    private ApiInfo paramApiInfo;
    private String paramPackageRest;

    public SwaggerConfigurationUtil(String packageRest, ApiInfo apiInfo) {
	super();

	this.paramApiInfo = apiInfo;
	this.paramPackageRest = packageRest;
    }

    public Docket produces() {
	List<Parameter> operationParameters = new ArrayList<>();
	operationParameters.add(new ParameterBuilder().name("Content-Type").description("Content-Type")
		.defaultValue(MediaType.APPLICATION_JSON_VALUE).modelRef(new ModelRef("string")).parameterType("header")
		.required(true).build());

	return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.paramApiInfo).select()
		.apis(RequestHandlerSelectors.basePackage(this.paramPackageRest)).paths(PathSelectors.any()).build()
		.enableUrlTemplating(Boolean.FALSE).produces(getAllProduceContentTypes())
		.consumes(getAllConsumeContentTypes()).globalOperationParameters(operationParameters);
    }

    private Set<String> getAllConsumeContentTypes() {
	Set<String> consumes = new HashSet<>();

	consumes.add(MediaType.APPLICATION_JSON_VALUE);
	return consumes;
    }

    private Set<String> getAllProduceContentTypes() {
	Set<String> produces = new HashSet<>();

	produces.add(MediaType.APPLICATION_JSON_VALUE);
	return produces;
    }

}

package com.github.ebaptistella.circuitbreaker.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@ApiModel
public class UFDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Código do estado")
    private Long id;

    @ApiModelProperty(notes = "Sigla do estado")
    private String sigla;

    @ApiModelProperty(notes = "Nome do estado")
    private String nome;

    @ApiModelProperty(notes = "Região que o estado pertence")
    private RegiaoDTO regiao;

}

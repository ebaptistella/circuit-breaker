package com.github.ebaptistella.circuitbreaker.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@ApiModel
public class RegiaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Código da região")
    private String id;

    @ApiModelProperty(notes = "Sigla da região")
    private String sigla;

    @ApiModelProperty(notes = "Nome da região")
    private String nome;
}

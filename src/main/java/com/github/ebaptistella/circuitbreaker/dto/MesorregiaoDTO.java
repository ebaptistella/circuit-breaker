package com.github.ebaptistella.circuitbreaker.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@ApiModel
public class MesorregiaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Código da Mesorregião")
    private Long id;

    @ApiModelProperty(notes = "Nome da Mesorregião")
    private String nome;

    @ApiModelProperty(notes = "Estado que a mesorregiões pertence")
    @JsonProperty("UF")
    private UFDTO uf;
}

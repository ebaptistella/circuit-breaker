package com.github.ebaptistella.circuitbreaker.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@ApiModel
public class MunicipioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Código do município")
    private Long id;

    @ApiModelProperty(notes = "Nome do município")
    private String nome;

    @ApiModelProperty(notes = "Microrregião que o município pertence")
    private MicrorregiaoDTO microrregiaoDTO;

}

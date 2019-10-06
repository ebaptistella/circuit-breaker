package com.github.ebaptistella.circuitbreaker.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@ApiModel
public class MicrorregiaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Código da Microrregião")
    private Long id;

    @ApiModelProperty(notes = "Nome da Microrregião")
    private String nome;

    @ApiModelProperty(notes = "Mesorregião que a microrregião pertence")
    private MesorregiaoDTO mesorregiao;
}

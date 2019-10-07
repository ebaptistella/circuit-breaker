package com.github.ebaptistella.circuitbreaker.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.ebaptistella.circuitbreaker.mapper.MunicipioToRetornoMunicipioMapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@XmlRootElement
@ApiModel
@JsonDeserialize(using = MunicipioToRetornoMunicipioMapper.class)
public class MunicipioRetornoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Código identificador do estado")
    private Long idEstado;

    @ApiModelProperty(notes = "Unidade Federativa")
    private String siglaEstado;

    @ApiModelProperty(notes = "Região que a cidade pertence")
    private String regiaoNome;

    @JsonIgnore
    private Long codigoCidade;

    @ApiModelProperty(notes = "Nome da cidade")
    private String nomeCidade;

    @ApiModelProperty(notes = "Nome da mesorregião")
    private String nomeMesorregiao;

    @ApiModelProperty(notes = "Nome da cidade/UF")
    private String nomeFormatado;

}

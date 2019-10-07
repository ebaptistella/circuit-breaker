package com.github.ebaptistella.circuitbreaker.mapper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.ebaptistella.circuitbreaker.dto.MunicipioRetornoDTO;

public class MunicipioToRetornoMunicipioMapper extends StdDeserializer<MunicipioRetornoDTO> {

    private static final long serialVersionUID = 1L;

    public MunicipioToRetornoMunicipioMapper() {
	this(null);
    }

    protected MunicipioToRetornoMunicipioMapper(Class<?> vc) {
	super(vc);
    }

    @Override
    public MunicipioRetornoDTO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
	JsonNode node = jp.getCodec().readTree(jp);

	MunicipioRetornoDTO retornoDTO = new MunicipioRetornoDTO();
	retornoDTO.setIdEstado(node.get("microrregiao").get("mesorregiao").get("UF").get("id").longValue());
	retornoDTO.setSiglaEstado(node.get("microrregiao").get("mesorregiao").get("UF").get("sigla").textValue());
	retornoDTO.setRegiaoNome(
		node.get("microrregiao").get("mesorregiao").get("UF").get("regiao").get("nome").textValue());
	retornoDTO.setCodigoCidade(node.get("id").longValue());
	retornoDTO.setNomeCidade(node.get("nome").textValue());
	retornoDTO.setNomeMesorregiao(node.get("microrregiao").get("mesorregiao").get("nome").textValue());
	retornoDTO.setNomeFormatado(retornoDTO.getNomeCidade().concat("/").concat(retornoDTO.getSiglaEstado()));

	return retornoDTO;
    }
}

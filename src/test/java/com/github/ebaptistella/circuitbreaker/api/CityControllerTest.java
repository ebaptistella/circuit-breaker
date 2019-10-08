package com.github.ebaptistella.circuitbreaker.api;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.CITY_REQUEST_MAPPING;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_PASS_USER_1;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_USER_1;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.ebaptistella.circuitbreaker.dto.MunicipioRetornoDTO;
import com.github.ebaptistella.circuitbreaker.service.CityService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CityController.class)
@ActiveProfiles("test")
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @MockBean
    private HttpServletResponse response;

    private List<MunicipioRetornoDTO> municipioRetornoDTOList = new ArrayList<>();
    private MunicipioRetornoDTO municipioDTO = new MunicipioRetornoDTO();

    @Before
    public void setup() {
	municipioDTO.setSiglaEstado("SC");
	municipioDTO.setSiglaEstado("PR");
	municipioDTO.setSiglaEstado("RS");
    }

    @Test
    public void clientError() throws Exception {
	municipioRetornoDTOList.clear();
	when(cityService.getAll()).thenReturn(new ArrayList<>());

	mockMvc.perform(get(CITY_REQUEST_MAPPING).with(httpBasic("", "")).accept(APPLICATION_JSON_VALUE)
		.contentType(APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void getAll_retorno_ok() throws Exception {
	municipioRetornoDTOList.clear();
	municipioRetornoDTOList.add(municipioDTO);
	municipioRetornoDTOList.add(municipioDTO);
	municipioRetornoDTOList.add(municipioDTO);

	when(cityService.getAll()).thenReturn(municipioRetornoDTOList);

	mockMvc.perform(get(CITY_REQUEST_MAPPING).with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1))
		.accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE)).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void getAll_retorno_lista_vazia() throws Exception {
	municipioRetornoDTOList.clear();
	when(cityService.getAll()).thenReturn(new ArrayList<>());

	mockMvc.perform(get(CITY_REQUEST_MAPPING).with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1))
		.accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE)).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void findByState_retorno_lista_vazia() throws Exception {
	municipioRetornoDTOList.clear();
	when(cityService.findByState(anyString())).thenReturn(new ArrayList<>());

	mockMvc.perform(get(CITY_REQUEST_MAPPING.concat("/uf/{stateCode}"), "35")
		.with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1)).accept(APPLICATION_JSON_VALUE)
		.contentType(APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void findByState_retorno_ok() throws Exception {
	municipioRetornoDTOList.clear();
	municipioRetornoDTOList.add(municipioDTO);
	municipioRetornoDTOList.add(municipioDTO);
	municipioRetornoDTOList.add(municipioDTO);

	when(cityService.findByState(anyString())).thenReturn(municipioRetornoDTOList);

	mockMvc.perform(get(CITY_REQUEST_MAPPING.concat("/uf/{stateCode}"), "35")
		.with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1)).accept(APPLICATION_JSON_VALUE)
		.contentType(APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void findByState_retorno_mais_um_estado() throws Exception {
	municipioRetornoDTOList.clear();
	municipioRetornoDTOList.add(municipioDTO);
	municipioRetornoDTOList.add(municipioDTO);
	municipioRetornoDTOList.add(municipioDTO);

	when(cityService.findByState(anyString())).thenReturn(municipioRetornoDTOList);

	mockMvc.perform(get(CITY_REQUEST_MAPPING.concat("/uf/{stateCode}"), "33|34")
		.with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1)).accept(APPLICATION_JSON_VALUE)
		.contentType(APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void findByName_retorno_ok() throws Exception {
	when(cityService.findByName(anyString())).thenReturn(anyLong());

	mockMvc.perform(get(CITY_REQUEST_MAPPING.concat("/{cityName}"), "São Miguel do Oeste")
		.with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1)).accept(APPLICATION_JSON_VALUE)
		.contentType(APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$").isNumber());
    }

    @Test
    public void findByName_retorno_nao_encontrado() throws Exception {
	when(cityService.findByName(anyString())).thenReturn(null);

	mockMvc.perform(get(CITY_REQUEST_MAPPING.concat("/{cityName}"), "São-Miguel-do-Oeste")
		.with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1)).accept(APPLICATION_JSON_VALUE)
		.contentType(APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("")));
    }

    @Test
    public void clearCache_retorno_ok() throws Exception {

	mockMvc.perform(delete(CITY_REQUEST_MAPPING).with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1))
		.accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().string(containsString("")));
    }

    @Test
    public void download_retorno_ok() throws Exception {
	StringWriter stringWriter = new StringWriter();
	PrintWriter writer = new PrintWriter(stringWriter);
	writer.write("id,name,age");
	writer.write("1,Mary,18");
	when(response.getWriter()).thenReturn(writer);

	mockMvc.perform(
		get(CITY_REQUEST_MAPPING.concat("/download")).with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1))
			.accept(APPLICATION_OCTET_STREAM_VALUE).contentType(APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
    }
}

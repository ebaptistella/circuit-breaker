package com.github.ebaptistella.circuitbreaker.api;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.STATE_REQUEST_MAPPING;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_PASS_USER_1;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_USER_1;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.ebaptistella.circuitbreaker.dto.RegiaoDTO;
import com.github.ebaptistella.circuitbreaker.dto.UFDTO;
import com.github.ebaptistella.circuitbreaker.service.StateService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StateController.class)
@ActiveProfiles("test")
public class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StateService stateService;

    private List<UFDTO> ufDTOList = new ArrayList<>();
    private UFDTO ufDTO = new UFDTO();

    @Before
    public void setup() {
	ufDTO.setNome("SC");
	ufDTO.setRegiao(new RegiaoDTO());
    }

    @Test
    public void clientError() throws Exception {
	ufDTOList.clear();
	when(stateService.getAll()).thenReturn(new ArrayList<>());

	mockMvc.perform(get(STATE_REQUEST_MAPPING).with(httpBasic("", "")).accept(APPLICATION_JSON_VALUE)
		.contentType(APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void getAll_retorno_ok() throws Exception {
	ufDTOList.clear();
	ufDTOList.add(ufDTO);
	ufDTOList.add(ufDTO);
	ufDTOList.add(ufDTO);

	when(stateService.getAll()).thenReturn(ufDTOList);

	mockMvc.perform(get(STATE_REQUEST_MAPPING).with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1))
		.accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE)).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void getAll_retorno_lista_vazia() throws Exception {
	ufDTOList.clear();
	when(stateService.getAll()).thenReturn(new ArrayList<>());

	mockMvc.perform(get(STATE_REQUEST_MAPPING).with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1))
		.accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE)).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void clearCache_retorno_ok() throws Exception {

	mockMvc.perform(delete(STATE_REQUEST_MAPPING).with(httpBasic(SECURITY_USER_1, SECURITY_PASS_USER_1))
		.accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().string(containsString("")));
    }

}

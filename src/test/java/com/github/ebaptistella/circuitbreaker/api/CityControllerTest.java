package com.github.ebaptistella.circuitbreaker.api;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAPIConstants.CITY_REQUEST_MAPPING;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_PASS_USER_1;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_USER_1;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;

import com.github.ebaptistella.circuitbreaker.api.impl.CityControllerImpl;
import com.github.ebaptistella.circuitbreaker.service.CityService;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
@ActiveProfiles("test")
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @Before
    public void setup() {
	this.mockMvc = MockMvcBuilders.standaloneSetup(new CityControllerImpl()).build();
    }

    @Test
    public void getAll_retorno_lista_vazia() throws Exception {
	when(cityService.getAll()).thenReturn(new ArrayList<>());

	mockMvc.perform(
		get(CITY_REQUEST_MAPPING)
			.header(AUTHORIZATION,
				"Basic " + Base64Utils.encodeToString(
					SECURITY_USER_1.concat(":").concat(SECURITY_PASS_USER_1).getBytes()))
			.accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE))
		.andDo(print());// .andExpect(status().isOk());
    }

}

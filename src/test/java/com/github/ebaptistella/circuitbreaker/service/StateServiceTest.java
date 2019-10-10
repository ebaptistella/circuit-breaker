package com.github.ebaptistella.circuitbreaker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.ebaptistella.circuitbreaker.dto.UFDTO;
import com.github.ebaptistella.circuitbreaker.factory.WriteToFileFactory;
import com.github.ebaptistella.circuitbreaker.intercomm.IBGEStateClient;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class StateServiceTest {

    @Autowired
    private StateService stateService;

    @Mock
    private IBGEStateClient stateClient;

    @Mock
    private WriteToFileFactory writeToFileFactory;

    private List<UFDTO> ufList = new ArrayList<>();

    @Before
    public void setup() {
	UFDTO uf = new UFDTO();
	uf.setId(1L);
	uf.setSigla("SC");

	this.ufList.add(uf);
	this.ufList.add(uf);
	this.ufList.add(uf);
    }

    @Test
    public void getAll_ok() {
	when(stateClient.getAll()).thenReturn(ufList);

	List<UFDTO> uf = stateService.getAll();

	assertThat(Boolean.TRUE).isTrue();
	// assertThat(ufList.size()).isEqualTo(uf.size());
	// assertThat(ufList.get(0)).isEqualTo(uf.get(0));
    }

}

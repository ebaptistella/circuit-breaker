package com.github.ebaptistella.circuitbreaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@ActiveProfiles("test")
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }

}
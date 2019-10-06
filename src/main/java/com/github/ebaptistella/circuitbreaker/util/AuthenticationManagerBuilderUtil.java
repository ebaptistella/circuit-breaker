package com.github.ebaptistella.circuitbreaker.util;

import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_ADMIN_1;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_PASS_ADMIN_1;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_PASS_NOOP;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_PASS_USER_1;
import static com.github.ebaptistella.circuitbreaker.constants.CircuitBreakerAuthConstants.SECURITY_USER_1;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public final class AuthenticationManagerBuilderUtil {

    private final AuthenticationManagerBuilder auth;

    public AuthenticationManagerBuilderUtil(AuthenticationManagerBuilder auth) {
	this.auth = auth;
    }

    public void builder() throws Exception {
	this.auth.inMemoryAuthentication().withUser(SECURITY_USER_1).password(SECURITY_PASS_NOOP + SECURITY_PASS_USER_1)
		.roles("USER").and().withUser(SECURITY_ADMIN_1).password(SECURITY_PASS_NOOP + SECURITY_PASS_ADMIN_1)
		.roles("USER", "ADMIN", "ACTUATOR");
    }

}

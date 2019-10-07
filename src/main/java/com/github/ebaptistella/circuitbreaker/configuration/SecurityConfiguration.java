package com.github.ebaptistella.circuitbreaker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.github.ebaptistella.circuitbreaker.util.AuthenticationManagerBuilderUtil;
import com.github.ebaptistella.circuitbreaker.util.HttpSecurityUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	new AuthenticationManagerBuilderUtil(auth).builder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	new HttpSecurityUtil(http).builder();
    }

}

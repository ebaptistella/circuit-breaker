package com.github.ebaptistella.circuitbreaker.util;

import java.util.Arrays;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

public class HttpSecurityUtil {
    private final HttpSecurity http;

    public HttpSecurityUtil(HttpSecurity http) {
        this.http = http;
    }

    public void builder() throws Exception {
        this.http.cors().configurationSource(this.corsBuilder()).and().httpBasic().and().authorizeRequests().antMatchers("/**").hasRole("USER").and()
                .csrf().disable().headers().frameOptions().disable();
    }

    private CorsConfigurationSource corsBuilder() {
        CorsConfiguration cors = new CorsConfiguration().applyPermitDefaultValues();
        cors.setAllowedMethods(Arrays.asList(HttpMethod.POST.toString(), HttpMethod.DELETE.toString(), HttpMethod.GET.toString(),
                HttpMethod.PATCH.toString(), HttpMethod.PUT.toString()));
        cors.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

}

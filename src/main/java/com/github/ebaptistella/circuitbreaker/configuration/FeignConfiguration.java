package com.github.ebaptistella.circuitbreaker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@Configuration
public class FeignConfiguration {

    @Bean
    public Logger feignLogger() {
        return new Slf4jLogger();
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }

}

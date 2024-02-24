package com.example.authenticate.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@Configuration
public class AuthenticateConfig {
    @Bean
    public RestTemplate getRestTemp(){
        return new RestTemplate();
    }

}

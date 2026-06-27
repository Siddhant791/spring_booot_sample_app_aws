package com.example.config;

import com.example.utility.HeaderValidatorHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Configuration
public class CustomBeans {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public RestClient getRestClient(){
        return RestClient.builder().build();
    }
}

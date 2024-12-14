package com.example.sampleawsapp;
//import com.example.sampleawsapp.FooConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
public class BeanConfig {

    @Bean
    @ConfigurationProperties(prefix = "foo.foo1")
    FooConfig fooConfig(){
        return new FooConfig();
    }
}
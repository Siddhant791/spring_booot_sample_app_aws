package com.example.sampleawsapp.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
public class BeanConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "roles")
//    RolesList rolesList(){
//        return new RolesList();
//    }

    @Bean
    @ConfigurationProperties(prefix = "foo.foo1")
    FooConfig fooConfig(){
        return new FooConfig();
    }
}
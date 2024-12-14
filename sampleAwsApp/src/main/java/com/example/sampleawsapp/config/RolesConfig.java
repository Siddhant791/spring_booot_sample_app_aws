package com.example.sampleawsapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
//import javax.annotation.PostConstruct;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "roles-list")
public class RolesConfig {
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

//    @PostConstruct
//    public void logRoles() {
//        System.out.println(roles);
//    }
}
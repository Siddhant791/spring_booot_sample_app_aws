package com.example.sampleawsapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "roles")
public class RolesConfig {
    private Map<String, Role> roles;

    // Getters and Setters
    public Map<String, Role> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "RolesConfig{" +
                "roles=" + roles +
                '}';
    }

    @PostConstruct
    public void printRoles() {
        if (roles == null || roles.isEmpty()) {
            System.out.println("Roles configuration is empty or not loaded!");
            return;
        }

        System.out.println("Loaded Roles Configuration:");
        roles.forEach((roleKey, roleValue) -> {
            System.out.println("Role Key: " + roleKey);
            System.out.println("  Lob: " + roleValue.getLob());
            System.out.println("  Message: " + roleValue.getMessage());
        });
    }
}

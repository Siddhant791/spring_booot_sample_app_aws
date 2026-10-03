package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class Security {

    @Bean
    SecurityFilterChain secureRestApi(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth ->
                        auth.requestMatchers(
                                "/actuator/health",
                                "/getComputer"
                        ).permitAll()
                                .anyRequest()
                                .authenticated()
                ).oauth2ResourceServer(
                        oAuth -> oAuth.jwt(Customizer.withDefaults())
                );

        return http.build();

    }
}

package com.example.config;

@FunctionalInterface
public interface HeaderValidator {

    void validate(String headerValue);
}

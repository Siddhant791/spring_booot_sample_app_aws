package com.example.utility;

import com.example.config.HeaderValidator;

import java.util.Set;

public final class HeaderValidatorHelper {

    public static HeaderValidator validator(Set<String> validateValues, String errorMessage) {
        return (headerValue) -> {
            if (!validateValues.contains(headerValue)) {
                throw new IllegalArgumentException(errorMessage);
            }
        };
    }

}

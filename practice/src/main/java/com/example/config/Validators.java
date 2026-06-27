package com.example.config;

import com.example.utility.HeaderValidatorHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class Validators {

    @Bean("Channel")
    public HeaderValidator channelValidator() {
        return HeaderValidatorHelper.validator(Set.of("EBU","MOBILE"), "Invalid channel");
    }

    @Bean("Country")
    public HeaderValidator countryValidator() {
        return HeaderValidatorHelper.validator(Set.of("US","IN"), "Invalid Country");
    }
}

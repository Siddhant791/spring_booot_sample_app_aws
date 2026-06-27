package com.example.config;

import com.example.enums.PaymentGateways;
import com.example.utility.HeaderValidatorHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Bean("PaymentGateway")
    public HeaderValidator paymentGatewayValidator() {
        Set<String> validPaymentGateways = Arrays.stream(PaymentGateways.values()).map(Enum::name).collect(Collectors.toSet());
        return HeaderValidatorHelper.validator(validPaymentGateways, "Payment gateway not supported");
    }
}

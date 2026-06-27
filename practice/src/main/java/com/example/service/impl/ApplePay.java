package com.example.service.impl;

import com.example.enums.PaymentGateways;
import com.example.service.PaymentGateway;
import org.springframework.stereotype.Component;

@Component
public class ApplePay implements PaymentGateway {
    @Override
    public PaymentGateways name() {
        return PaymentGateways.APPLEPAY;
    }

    @Override
    public String pay() {
        return "Paying from apple pay";
    }
}

package com.example.service;

import com.example.enums.PaymentGateways;

@FunctionalInterface
public interface PaymentService {
    String payment(PaymentGateways gatewayName);
}

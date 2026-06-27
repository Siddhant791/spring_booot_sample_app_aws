package com.example.service.impl;

import com.example.enums.PaymentGateways;
import com.example.service.PaymentGateway;
import com.example.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final List<PaymentGateway> paymentGateways;

    @Override
    public String payment(PaymentGateways gatewayName) {
        return paymentGateways
                .stream()
                .filter(gateway -> gateway.name().name().equals(gatewayName.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid payment gateway"))
                .pay();
    }
}

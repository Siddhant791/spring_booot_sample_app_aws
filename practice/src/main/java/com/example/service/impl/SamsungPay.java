package com.example.service.impl;

import com.example.enums.PaymentGateways;
import com.example.service.PaymentGateway;
import org.springframework.stereotype.Component;

@Component
public class SamsungPay implements PaymentGateway {

    @Override
    public PaymentGateways name() {
        return PaymentGateways.SAMSUNGPAY;
    }

    @Override
    public String pay() {
        return "Paying from samsung pay";
    }
}

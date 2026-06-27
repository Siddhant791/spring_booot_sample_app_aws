package com.example.service;

import com.example.enums.PaymentGateways;

public interface PaymentGateway {

    PaymentGateways name();
    String pay();
}

package com.example.service.circulardependency;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void createOrder(){
        paymentService.pay();
    }

    public void validateOrder(){
        System.out.println("validating order");
    }
}

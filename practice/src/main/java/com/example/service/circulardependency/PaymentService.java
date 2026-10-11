package com.example.service.circulardependency;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final OrderService orderService;

    public PaymentService(@Lazy OrderService orderService){
        this.orderService = orderService;
    }

    public void pay() {
        orderService.validateOrder();
        System.out.println("Now pay");
    }
}

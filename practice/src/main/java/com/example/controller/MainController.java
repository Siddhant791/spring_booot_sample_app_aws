package com.example.controller;

import com.example.dto.Computer;
import com.example.dto.Mobile;
import com.example.enums.PaymentGateways;
import com.example.config.HeaderValidator;
import com.example.service.ComputerService;
import com.example.service.ObjectsService;
import com.example.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ObjectsService objectsService;
    private final Map<String, HeaderValidator> headerValidators;
    private final PaymentService paymentService;
    private final ComputerService computerService;

    @GetMapping("/health")
    public String healthCheck(@RequestHeader("x-channel-id") String channelId) {
        headerValidators.get("Channel").validate(channelId);
        return "App is running fine";

    }

    @PostMapping("/objects")
    public ResponseEntity<?> getObjects(){
//        return objectsService.getAllObjects();
        return objectsService.getObjectsUsingRestClient();
    }

    @GetMapping("/pay")
    public String payment(@RequestHeader("payment-gateway") PaymentGateways paymentGatewayName , @RequestHeader("x-channel-id") String channelId) {
        headerValidators.get("PaymentGateway").validate(paymentGatewayName.name());
        headerValidators.get("Channel").validate(channelId);
        return paymentService.payment(paymentGatewayName);
    }

    @GetMapping("/getComputer")
    public ResponseEntity<?> getComputer() {
        List<Mobile> computerList = computerService.getData();
        return ResponseEntity.ok(computerList);
    }

}

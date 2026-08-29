package com.example.controller;

import com.example.dto.MobileRequest;
import com.example.dto.MobileResponse;
import com.example.enums.PaymentGateways;
import com.example.config.HeaderValidator;
import com.example.service.ComputerService;
import com.example.service.ObjectsService;
import com.example.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//        return objectsService.getObjectsUsingRestClient();
        return ResponseEntity.ok("ok");
    }


    @GetMapping("/pay")
    public String payment(@RequestHeader("payment-gateway") PaymentGateways paymentGatewayName , @RequestHeader("x-channel-id") String channelId) {
        headerValidators.get("PaymentGateway").validate(paymentGatewayName.name());
        headerValidators.get("Channel").validate(channelId);
        return paymentService.payment(paymentGatewayName);
    }

    @GetMapping("/getComputer")
    public ResponseEntity<?> getComputer() {
        List<MobileResponse> computerList = computerService.getData();
        return ResponseEntity.ok(computerList);
    }


    @PostMapping("/add")
    public ResponseEntity<MobileResponse> addMobile(@RequestBody MobileRequest mobileRequest) {
        MobileResponse mobileResponse = computerService.addMobile(mobileRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(mobileResponse);
    }

}

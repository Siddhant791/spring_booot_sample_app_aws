package com.example.controller;

import com.example.config.HeaderValidator;
import com.example.service.ObjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ObjectsService objectsService;
    private final Map<String, HeaderValidator> headerValidators;

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

}

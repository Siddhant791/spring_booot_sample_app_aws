package com.example.service.impl;

import com.example.config.RestApiClient;
import com.example.dto.MobileRequest;
import com.example.dto.MobileResponse;
import com.example.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ComputerServiceImpl implements ComputerService {

    private final RestApiClient restApiClient;
    private final RestClient restClient;

    @Override
    public List<MobileResponse> getData() {
        return restClient
                .get()
                .uri("/objects")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public MobileResponse addMobile(MobileRequest mobileRequest) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return restClient
                .post()
                .uri("/objects")
                .headers(headers -> headers.addAll(httpHeaders))
                .body(mobileRequest)
                .retrieve()
                .body(MobileResponse.class);
    }
}

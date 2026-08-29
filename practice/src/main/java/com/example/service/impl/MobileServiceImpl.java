package com.example.service.impl;

import com.example.config.RestApiClient;
import com.example.dto.MobileRequest;
import com.example.dto.MobileResponse;
import com.example.dto.error.ErrorMobileResponse;
import com.example.service.MobileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MobileServiceImpl implements MobileService {

    private final RestApiClient restApiClient;
    private final RestClient restClient;
    private final ObjectMapper objectMapper;

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

    @Override
    public MobileResponse getMobile(String productId) {
        return restClient
                .get()
                .uri("/objects/{id}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    ErrorMobileResponse errorMobileResponse = objectMapper.readValue(response.getBody(), ErrorMobileResponse.class);
                    throw new RuntimeException(errorMobileResponse.error());
                })
                .body(MobileResponse.class);
    }
}

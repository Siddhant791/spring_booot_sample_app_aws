package com.example.service.impl;

import com.example.config.RestApiClient;
import com.example.dto.Computer;
import com.example.dto.Mobile;
import com.example.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ComputerServiceImpl implements ComputerService {

    private final RestApiClient restApiClient;
    private final RestClient restClient;

    @Override
    public List<Mobile> getData() {
        return restClient
                .get()
                .uri("/objects")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}

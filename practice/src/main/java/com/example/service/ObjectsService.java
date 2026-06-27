package com.example.service;

import com.example.dto.Computer;
import com.example.dto.MyData;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ObjectsService {

    private final RestTemplate restTemplate;
    private final RestClient restClient;

    public ResponseEntity<?> getAllObjects() {
//        return restTemplate.getForObject("https://api.restful-api.dev/objects",JsonNode.class);
//        return restTemplate.getForEntity("https://api.restful-api.dev/objects",String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Computer requestBody = new Computer();
        requestBody.setName("Siddhant");
        MyData myData = MyData.builder().year(1998).hardDiskSize("Siddhant hard disk").price(89.99).cpuModel("core i5").build();

        requestBody.setData(myData);
        HttpEntity<Computer> request = new HttpEntity<>(requestBody,headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange("https://api.restful-api.dev/objects", HttpMethod.POST, request, JsonNode.class);
        return response;
    }

    public ResponseEntity<String> getObjectsUsingRestClient() {
//        return restClient.method(HttpMethod.GET).uri("https://api.restful-api.dev/objects").retrieve().toEntity(String.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Computer requestBody = new Computer();
        requestBody.setName("Siddhant");
        MyData myData = MyData.builder().year(1998).hardDiskSize("Siddhant hard disk").price(89.99).cpuModel("core i5").build();

        requestBody.setData(myData);
        HttpEntity<Computer> request = new HttpEntity<>(requestBody,headers);

        return restClient.method(HttpMethod.POST).uri("https://api.restful-api.dev/objects").headers(header -> headers.setContentType(MediaType.APPLICATION_JSON)).body(requestBody).retrieve().toEntity(String.class);
    }
}

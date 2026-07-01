package com.example.service;

import com.example.dto.Computer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.function.Consumer;
import org.mockito.ArgumentCaptor;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ObjectsServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestClient restClient;

    @InjectMocks
    private ObjectsService objectsService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    private RestClient.RequestBodyUriSpec mockBodyUriSpec;
    private RestClient.RequestBodySpec mockBodySpec;
    private RestClient.ResponseSpec mockResponseSpec;

    private void setupRestClientMocks() {
        mockBodyUriSpec = mock(RestClient.RequestBodyUriSpec.class);
        mockBodySpec = mock(RestClient.RequestBodySpec.class);
        mockResponseSpec = mock(RestClient.ResponseSpec.class);

        doReturn(mockBodyUriSpec).when(restClient).method(HttpMethod.POST);
        doReturn(mockBodySpec).when(mockBodyUriSpec).uri(anyString());
        doReturn(mockBodySpec).when(mockBodySpec).headers(any());
        doReturn(mockBodySpec).when(mockBodySpec).body(any(Computer.class));
        doReturn(mockResponseSpec).when(mockBodySpec).retrieve();
    }

    // ==================== RestTemplate Tests ====================

    @Test
    void getAllObjects_shouldReturnSuccessResponse() {
        ObjectNode responseJson = objectMapper.createObjectNode();
        responseJson.put("id", "1");
        responseJson.put("name", "Siddhant");

        ResponseEntity<JsonNode> mockResponse = new ResponseEntity<>(responseJson, HttpStatus.CREATED);
        when(restTemplate.exchange(
                eq("https://api.restful-api.dev/objects"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(JsonNode.class)
        )).thenReturn(mockResponse);

        ResponseEntity<?> response = objectsService.getAllObjects();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        JsonNode body = (JsonNode) response.getBody();
        assertThat(body.get("name").asText()).isEqualTo("Siddhant");
        assertThat(body.get("id").asText()).isEqualTo("1");

        verify(restTemplate, times(1)).exchange(
                eq("https://api.restful-api.dev/objects"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(JsonNode.class)
        );
    }

    @Test
    void getAllObjects_shouldSendCorrectContentTypeHeader() {
        ObjectNode responseJson = objectMapper.createObjectNode();
        responseJson.put("id", "1");
        ResponseEntity<JsonNode> mockResponse = new ResponseEntity<>(responseJson, HttpStatus.CREATED);
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class)
        )).thenReturn(mockResponse);

        objectsService.getAllObjects();

        verify(restTemplate).exchange(
                anyString(),
                any(HttpMethod.class),
                argThat(httpEntity -> {
                    HttpHeaders headers = httpEntity.getHeaders();
                    return headers.getContentType() != null &&
                            headers.getContentType().equals(MediaType.APPLICATION_JSON);
                }),
                any(Class.class)
        );
    }

    @Test
    void getAllObjects_shouldSendCorrectRequestBody() {
        ObjectNode responseJson = objectMapper.createObjectNode();
        responseJson.put("id", "1");
        ResponseEntity<JsonNode> mockResponse = new ResponseEntity<>(responseJson, HttpStatus.CREATED);
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class)
        )).thenReturn(mockResponse);

        objectsService.getAllObjects();

        verify(restTemplate).exchange(
                anyString(),
                any(HttpMethod.class),
                argThat(httpEntity -> {
                    Object body = httpEntity.getBody();
                    if (!(body instanceof Computer computer)) return false;
                    return "Siddhant".equals(computer.getName()) &&
                            computer.getData() != null &&
                            computer.getData().getYear() == 1998 &&
                            "Siddhant hard disk".equals(computer.getData().getHardDiskSize()) &&
                            computer.getData().getPrice() == 89.99 &&
                            "core i5".equals(computer.getData().getCpuModel());
                }),
                any(Class.class)
        );
    }

    @Test
    void getAllObjects_shouldUseCorrectUrl() {
        ObjectNode responseJson = objectMapper.createObjectNode();
        responseJson.put("id", "1");
        ResponseEntity<JsonNode> mockResponse = new ResponseEntity<>(responseJson, HttpStatus.CREATED);
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class)
        )).thenReturn(mockResponse);

        objectsService.getAllObjects();

        verify(restTemplate).exchange(
                eq("https://api.restful-api.dev/objects"),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class)
        );
    }

    @Test
    void getAllObjects_shouldUsePostMethod() {
        ObjectNode responseJson = objectMapper.createObjectNode();
        responseJson.put("id", "1");
        ResponseEntity<JsonNode> mockResponse = new ResponseEntity<>(responseJson, HttpStatus.CREATED);
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class)
        )).thenReturn(mockResponse);

        objectsService.getAllObjects();

        verify(restTemplate).exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                any(Class.class)
        );
    }

    @Test
    void getAllObjects_shouldThrowExceptionWhenRestTemplateFails() {
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class)
        )).thenThrow(new RestClientResponseException(
                "Service Unavailable",
                503,
                "Service Unavailable",
                HttpHeaders.EMPTY,
                new byte[0],
                null
        ));

        assertThatThrownBy(() -> objectsService.getAllObjects())
                .isInstanceOf(RestClientResponseException.class)
                .hasMessageContaining("Service Unavailable");
    }

    @Test
    void getAllObjects_shouldReturnServerErrorResponse() {
        ResponseEntity<JsonNode> mockResponse = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class)
        )).thenReturn(mockResponse);

        ResponseEntity<?> response = objectsService.getAllObjects();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    // ==================== RestClient Tests ====================

    @Test
    void getObjectsUsingRestClient_shouldReturnSuccessResponse() {
        setupRestClientMocks();
        String responseBody = "{\"id\":\"1\",\"name\":\"Siddhant\"}";
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.CREATED);
        doReturn(mockResponse).when(mockResponseSpec).toEntity(String.class);

        ResponseEntity<String> response = objectsService.getObjectsUsingRestClient();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(responseBody);

        verify(restClient).method(HttpMethod.POST);
        verify(mockBodyUriSpec).uri("https://api.restful-api.dev/objects");
        verify(mockBodySpec).retrieve();
        verify(mockResponseSpec).toEntity(String.class);
    }

    @Test
    void getObjectsUsingRestClient_shouldSendCorrectRequestBody() {
        setupRestClientMocks();
        doReturn(new ResponseEntity<>("{}", HttpStatus.CREATED)).when(mockResponseSpec).toEntity(String.class);

        objectsService.getObjectsUsingRestClient();

        ArgumentCaptor<Computer> captor = ArgumentCaptor.forClass(Computer.class);
        verify(mockBodySpec).body(captor.capture());
        Computer captured = captor.getValue();
        assertThat(captured.getName()).isEqualTo("Siddhant");
        assertThat(captured.getData()).isNotNull();
        assertThat(captured.getData().getYear()).isEqualTo(1998);
        assertThat(captured.getData().getHardDiskSize()).isEqualTo("Siddhant hard disk");
        assertThat(captured.getData().getPrice()).isEqualTo(89.99);
        assertThat(captured.getData().getCpuModel()).isEqualTo("core i5");
    }

    @Test
    void getObjectsUsingRestClient_shouldUseCorrectUrl() {
        setupRestClientMocks();
        doReturn(new ResponseEntity<>("{}", HttpStatus.CREATED)).when(mockResponseSpec).toEntity(String.class);

        objectsService.getObjectsUsingRestClient();

        verify(mockBodyUriSpec).uri("https://api.restful-api.dev/objects");
    }

    @Test
    void getObjectsUsingRestClient_shouldSetContentTypeHeader() {
        setupRestClientMocks();
        doReturn(new ResponseEntity<>("{}", HttpStatus.CREATED)).when(mockResponseSpec).toEntity(String.class);

        objectsService.getObjectsUsingRestClient();

        verify(mockBodySpec).headers(any());
    }

    @Test
    void getObjectsUsingRestClient_shouldThrowExceptionWhenRequestFails() {
        setupRestClientMocks();
        doThrow(new RestClientResponseException(
                "Bad Request",
                400,
                "Bad Request",
                HttpHeaders.EMPTY,
                new byte[0],
                null
        )).when(mockBodySpec).retrieve();

        assertThatThrownBy(() -> objectsService.getObjectsUsingRestClient())
                .isInstanceOf(RestClientResponseException.class)
                .hasMessageContaining("Bad Request");

        verify(restClient).method(HttpMethod.POST);
        verify(mockBodyUriSpec).uri(anyString());
    }

    @Test
    void getObjectsUsingRestClient_shouldReturnNotFoundResponse() {
        setupRestClientMocks();
        doReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND)).when(mockResponseSpec).toEntity(String.class);

        ResponseEntity<String> response = objectsService.getObjectsUsingRestClient();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void getObjectsUsingRestClient_shouldUsePostMethod() {
        setupRestClientMocks();
        doReturn(new ResponseEntity<>("{}", HttpStatus.CREATED)).when(mockResponseSpec).toEntity(String.class);

        objectsService.getObjectsUsingRestClient();

        verify(restClient).method(HttpMethod.POST);
    }
}

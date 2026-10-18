package com.example.dto.sqs;

public record MessageRequest(String queueName, String message) {
}

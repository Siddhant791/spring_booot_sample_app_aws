package com.example.service;

import com.example.dto.sqs.MessageRequest;

import java.util.List;

public interface SqsQueueService {

    public List<String> getQueueList();
    public void sendMessage(MessageRequest messageRequest);
}

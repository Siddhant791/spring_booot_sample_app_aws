package com.example.service.impl;

import com.example.service.SqsQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SqsQueueServiceImpl implements SqsQueueService {

    private final SqsClient sqsClient;

    @Override
    public List<String> getQueueList() {

        return sqsClient
                .listQueues()
                .queueUrls()
                .stream()
                .map(s -> s.substring(s.lastIndexOf("/") + 1))
                .toList();
    }
}

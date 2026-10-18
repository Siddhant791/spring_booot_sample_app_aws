package com.example.service.impl;

import com.example.dto.sqs.MessageRequest;
import com.example.service.SqsQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public void sendMessage(MessageRequest messageRequest) {
        String queueUrl = sqsClient.getQueueUrl(
                GetQueueUrlRequest
                        .builder()
                        .queueName(messageRequest.queueName())
                        .build()
        ).queueUrl();

        SendMessageRequest message = SendMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .messageBody(messageRequest.message())
                .build();
        SendMessageResponse messageResponse = sqsClient.sendMessage(message);
        log.info("Message sent successfully with message {}", messageResponse.messageId());
    }
}

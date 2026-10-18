package com.example.controller;

import com.example.service.S3Service;
import com.example.service.SqsQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AwsController {

    private final S3Service s3Service;
    private final SqsQueueService sqsQueueService;

    @GetMapping("/getS3Files")
    public List<String> getS3FileNames() {
        return s3Service.getFiles();
    }

    @GetMapping("/getSqsQueueList")
    public List<String> getSqsQueueList() {
        return sqsQueueService.getQueueList();
    }
}

package com.example.service;

import com.example.dto.MobileRequest;
import com.example.dto.MobileResponse;

import java.util.List;

public interface ComputerService {
    List<MobileResponse> getData();
    MobileResponse addMobile(MobileRequest mobileRequest);
}

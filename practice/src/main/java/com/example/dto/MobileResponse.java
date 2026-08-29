package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MobileResponse(String id, @JsonProperty("data") MobileData mobileData) {
}


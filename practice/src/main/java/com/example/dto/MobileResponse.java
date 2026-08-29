package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MobileResponse(String id, String name, @JsonProperty("data") MobileData mobileData) {
}


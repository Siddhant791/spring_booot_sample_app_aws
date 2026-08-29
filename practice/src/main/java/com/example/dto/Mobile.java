package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Mobile(long id, @JsonProperty("data") MobileData mobileData) {
}


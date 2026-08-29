package com.example.dto.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST("Invalid request"),
    NOT_FOUND("Not found");

    private final String errorMessage;
}

package com.example.dto.error;

import lombok.Builder;

@Builder
public record FinalErrorResponse(String errorMessage, ErrorCode errorCode) {
}

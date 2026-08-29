package com.example.exception;

import com.example.dto.error.ErrorCode;
import com.example.dto.error.FinalErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<FinalErrorResponse> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.badRequest().body(FinalErrorResponse.builder().errorMessage(exception.getErrorMessage()).errorCode(ErrorCode.NOT_FOUND).build());
    }
}

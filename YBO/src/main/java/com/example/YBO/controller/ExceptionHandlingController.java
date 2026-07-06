package com.example.YBO.controller;

import com.example.YBO.error.AuthOrRegisterException;
import com.example.YBO.error.CreatingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(AuthOrRegisterException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthOrRegisterException e) {
        return ResponseEntity.status(e.getErrorResult().getStatus())
                .body(new ErrorResponse(e.getErrorResult().getStatus().toString(),
                        e.getErrorResult().getMessage()));
    }

    @ExceptionHandler(CreatingException.class)
    public ResponseEntity<ErrorResponse> handleCreateException(CreatingException e) {
        return ResponseEntity.status(e.getCreatingErrorResult().getStatus())
                .body(new ErrorResponse(e.getCreatingErrorResult().getStatus().toString(),
                        e.getCreatingErrorResult().getMessage()));
    }

    record ErrorResponse(String code, String message){}
}
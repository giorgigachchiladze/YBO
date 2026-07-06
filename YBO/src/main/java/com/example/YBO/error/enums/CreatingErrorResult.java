package com.example.YBO.error.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CreatingErrorResult {

;

    private final HttpStatus status;
    private final String message;
}

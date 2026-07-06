package com.example.YBO.error;

import com.example.YBO.error.enums.CreatingErrorResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreatingException extends RuntimeException {
    private final CreatingErrorResult creatingErrorResult;
}

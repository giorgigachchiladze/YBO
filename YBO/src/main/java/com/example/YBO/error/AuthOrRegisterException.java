package com.example.YBO.error;

import com.example.YBO.error.enums.AuthOrRegisterErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthOrRegisterException extends RuntimeException {
    private final AuthOrRegisterErrorResult errorResult;
}

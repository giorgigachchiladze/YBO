package com.example.YBO.error.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthOrRegisterErrorResult {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User with such username was not found"),
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "company with such username was not found"),
    PASSWORD_DOES_NOT_MATCH(HttpStatus.CONFLICT, "Username or Password is incorrect"),
    USERNAME_IS_ALREADY_TAKEN(HttpStatus.CONFLICT, "username is already taken"),
    INVALID_USERNAME(HttpStatus.CONFLICT, "username must contain at least 3 and max 20 symbols" +
            " and should only contain letters, numbers and _ - symbols"),
    INVALID_PASSWORD(HttpStatus.CONFLICT, "password must contain at least 8 and max 20 symbols");
    private final HttpStatus status;
    private final String message;
}

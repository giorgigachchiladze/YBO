package com.example.YBO.dto;

public record AuthResponse(
        String token,
        String role
) {
}

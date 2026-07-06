package com.example.YBO.dto;


import jakarta.validation.constraints.NotBlank;

//since user and company comes with different columns for registration they get different dto for it
//but logIn only asks for email and password for both entities
//so they have same dto for logIn and authResponse.
public record LogInRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}

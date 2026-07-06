package com.example.YBO.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CVDto {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotNull
    Instant birthday;
    @NotBlank
    @Email(message = "Invalid email format!")
    String email;
    @NotBlank
    String phoneNumber;
    @NotBlank
    String address;
    @NotBlank
    String profession;
    @NotBlank
    String aboutMe;
    @NotBlank
    String experience;
    @NotBlank
    String education;
    @NotBlank
    String skills;
    @NotBlank
    String languages;
}

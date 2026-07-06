package com.example.YBO.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRegisterRequest {
    @NotBlank
    String companyName;
    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank
    String phoneNumber;
    @NotBlank
    String address;
    @NotBlank
    String companyDescription;
}

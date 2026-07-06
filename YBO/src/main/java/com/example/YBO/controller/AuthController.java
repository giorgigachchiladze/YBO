package com.example.YBO.controller;

import com.example.YBO.dto.CompanyRegisterRequest;
import com.example.YBO.dto.LogInRequest;
import com.example.YBO.dto.UserRegisterRequest;
import com.example.YBO.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ybo/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LogInRequest loginRequest) {
        return ResponseEntity.ok().body(authService.logIn(loginRequest));
    }

    @PostMapping("/register/company")
    public ResponseEntity<Void> register(@Valid @RequestBody CompanyRegisterRequest registerRequest) {
        authService.registerCompany(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(null);
    }

    @PostMapping("/register/user")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRegisterRequest registerRequest) {
        authService.registerAppUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(null);
    }
}

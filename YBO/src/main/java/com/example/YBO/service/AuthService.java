package com.example.YBO.service;

import com.example.YBO.dto.AuthResponse;
import com.example.YBO.dto.CompanyRegisterRequest;
import com.example.YBO.dto.LogInRequest;
import com.example.YBO.dto.UserRegisterRequest;
import com.example.YBO.error.AuthOrRegisterException;
import com.example.YBO.error.enums.AuthOrRegisterErrorResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.unauthenticated;

//the AuthService class makes sure that service doesn't put real password
//in database so it uses password encoder for user's and company's registration
//and logIn method works for company and user, it uses json web token and authentication manager.

@Service
public class AuthService {
    private final AppUserService appUserService;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ValidationService validationService;

    public AuthService(AppUserService appUserService,
                      CompanyService companyService,
                      PasswordEncoder passwordEncoder,
                      @Lazy AuthenticationManager authenticationManager,
                      JwtService jwtService, ValidationService validationService) {
       this.appUserService = appUserService;
       this.companyService = companyService;
       this.passwordEncoder = passwordEncoder;
       this.authenticationManager = authenticationManager;
       this.jwtService = jwtService;
       this.validationService = validationService;
    }

    public void registerCompany(CompanyRegisterRequest companyRegisterRequest) {
        validationService.usernameValidation(companyRegisterRequest.getUsername());
        validationService.passwordValidation(companyRegisterRequest.getPassword());
        String encodedPassword = passwordEncoder.encode(companyRegisterRequest.getPassword());
        companyRegisterRequest.setPassword(encodedPassword);
        companyService.saveCompany(companyRegisterRequest);
    }

    public void registerAppUser(UserRegisterRequest userRegisterRequest) {
        validationService.usernameValidation(userRegisterRequest.getUsername());
        validationService.passwordValidation(userRegisterRequest.getPassword());
        String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(encodedPassword);
        appUserService.saveUser(userRegisterRequest);
    }

    public AuthResponse logIn(LogInRequest logInRequest) {
        try {
            Authentication auth = unauthenticated(
                    logInRequest.username(),
                    logInRequest.password()
            );

            Authentication authentication = authenticationManager.authenticate(auth);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);
            return new AuthResponse(token, userDetails.getAuthorities().toString());
        } catch (AuthenticationException e) {
            throw new AuthOrRegisterException(AuthOrRegisterErrorResult.PASSWORD_DOES_NOT_MATCH);
        }
    }

    @Bean
    public UserDetailsService userDetailsService(
            com.example.YBO.repository.AppUserRepository appUserRepository,
            com.example.YBO.repository.CompanyRepository companyRepository) {

        return username -> {
            var appUser = appUserRepository.findByUsername(username);
            if (appUser.isPresent()) {
                return appUser.get();
            }

            var company = companyRepository.findByUsername(username);
            if (company.isPresent()) {
                return company.get();
            }

            throw new UsernameNotFoundException("USER/NOT/FOUND " + username);
        };
    }

}


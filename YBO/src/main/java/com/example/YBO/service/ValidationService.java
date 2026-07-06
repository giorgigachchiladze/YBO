package com.example.YBO.service;

import com.example.YBO.error.AuthOrRegisterException;
import com.example.YBO.error.enums.AuthOrRegisterErrorResult;
import com.example.YBO.repository.AppUserRepository;
import com.example.YBO.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidationService {

    private final AppUserRepository appUserRepository;
    private final CompanyRepository companyRepository;

    public boolean usernameValidation(String username) {
        if(!username.matches("^[a-zA-Z0-9_]{3,20}$")) {
                throw new AuthOrRegisterException(AuthOrRegisterErrorResult.INVALID_USERNAME);
        }
        if (appUserRepository.findByUsername(username).isPresent()
                || companyRepository.findByUsername(username).isPresent()) {
            throw new AuthOrRegisterException(AuthOrRegisterErrorResult.USERNAME_IS_ALREADY_TAKEN);
        }
        else  return true;
    }

    public boolean passwordValidation(String password) {
        if(!password.matches("^\\S{8,20}$")) {
            throw new AuthOrRegisterException(AuthOrRegisterErrorResult.INVALID_PASSWORD);
        }
        else  return true;
    }

}

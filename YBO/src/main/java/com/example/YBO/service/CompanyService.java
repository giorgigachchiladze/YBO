package com.example.YBO.service;

import com.example.YBO.dto.CompanyRegisterRequest;
import com.example.YBO.error.AuthOrRegisterException;
import com.example.YBO.error.enums.AuthOrRegisterErrorResult;
import com.example.YBO.model.Company;
import com.example.YBO.repository.CompanyRepository;
import com.example.YBO.roles.Roles;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService implements UserDetailsService {

    private final CompanyRepository companyRepository;

    //builds and saves user in database
    public void saveCompany(CompanyRegisterRequest companyRegisterRequest) {
        companyRepository.save(
                Company.builder()
                        .companyName(companyRegisterRequest.getCompanyName())
                        .username(companyRegisterRequest.getUsername())
                        .password(companyRegisterRequest.getPassword())
                        .phoneNumber(companyRegisterRequest.getPhoneNumber())
                        .address(companyRegisterRequest.getAddress())
                        .companyDescription(companyRegisterRequest.getCompanyDescription())
                        .role(Roles.COMPANY)
                        .build());

    }

    //finds user by its username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return companyRepository.findByUsername(username)
                .orElseThrow(() -> new AuthOrRegisterException(AuthOrRegisterErrorResult.COMPANY_NOT_FOUND));
    }
}

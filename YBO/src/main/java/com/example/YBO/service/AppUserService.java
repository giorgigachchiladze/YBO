package com.example.YBO.service;

import com.example.YBO.dto.UserRegisterRequest;
import com.example.YBO.error.AuthOrRegisterException;
import com.example.YBO.error.enums.AuthOrRegisterErrorResult;
import com.example.YBO.model.AppUser;
import com.example.YBO.repository.AppUserRepository;
import com.example.YBO.roles.Roles;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    //builds and saves user in database
    public void saveUser(UserRegisterRequest userRegisterRequest) {
        appUserRepository.save(
                AppUser.builder()
                        .username(userRegisterRequest.getUsername())
                        .password(userRegisterRequest.getPassword())
                        .role(Roles.USER)
                        .build());
    }

    //finds user by its username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new AuthOrRegisterException(AuthOrRegisterErrorResult.USER_NOT_FOUND));
    }
}

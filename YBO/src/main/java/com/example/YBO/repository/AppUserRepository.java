package com.example.YBO.repository;

import com.example.YBO.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

//repository helps us to control AppUser entity easily.

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    //method helps us find user by username.
    Optional<AppUser> findByUsername(String username);
}

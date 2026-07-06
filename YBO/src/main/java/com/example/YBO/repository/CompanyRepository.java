package com.example.YBO.repository;

import com.example.YBO.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

//repository helps us to control Company entity easily.

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    //method helps us find user by username.
    Optional<Company> findByUsername(String username);
}

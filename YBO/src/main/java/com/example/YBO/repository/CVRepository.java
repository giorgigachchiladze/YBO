package com.example.YBO.repository;

import com.example.YBO.model.AppUser;
import com.example.YBO.model.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

//repository helps us to control CV entity easily.

@Repository
public interface CVRepository extends JpaRepository<CV, UUID> {
    Optional<CV> findByUserId(UUID userId);
}

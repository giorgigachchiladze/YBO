package com.example.YBO.repository;

import com.example.YBO.model.JobRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//repository helps us to control JobRequest entity easily.

@Repository
public interface JobRequestRepository extends JpaRepository<JobRequest, UUID> {
    Page<JobRequest> findByOpeningId(UUID id, Pageable pageable);
    Page<JobRequest> findByCvId(UUID id, Pageable pageable);
}

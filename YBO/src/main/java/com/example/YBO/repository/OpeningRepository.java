package com.example.YBO.repository;


import com.example.YBO.model.Opening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

//repository helps us to control Opening entity easily.

@Repository
public interface OpeningRepository extends JpaRepository<Opening, UUID> {
    Page<Opening> findByCompanyId(UUID companyId, Pageable pageable);
}

package com.example.YBO.service;

import com.example.YBO.dto.OpeningDto;
import com.example.YBO.model.Company;
import com.example.YBO.model.Opening;
import com.example.YBO.repository.CompanyRepository;
import com.example.YBO.repository.OpeningRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OpeningService {
    private final OpeningRepository openingRepository;
    private final CompanyRepository companyRepository;

    public void saveOpening(OpeningDto openingDto) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Company company = companyRepository.findByUsername(currentUsername).get();

        openingRepository.save(Opening
                .builder()
                .address(company.getAddress())
                .companyName(company.getCompanyName())
                .phoneNumber(company.getPhoneNumber())
                .experienceNeeded(openingDto.getExperienceNeeded())
                .profession(openingDto.getProfession())
                .salary(openingDto.getSalary())
                .schedule(openingDto.getSchedule())
                .jobToDo(openingDto.getJobToDo())
                .company(company)
                .build());
    }


    public Page<Opening> getAllOpenings(Pageable pageable) {
        return openingRepository.findAll(pageable);
    }

    public Opening getOpeningById(UUID id) {
        return openingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opening not found with id: " + id));
    }

    public Page<Opening> getAllOpeningsAsCompany(Pageable pageable) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Company company = companyRepository.findByUsername(currentUsername).get();

        return openingRepository.findByCompanyId(company.getId(), pageable);
    }
}

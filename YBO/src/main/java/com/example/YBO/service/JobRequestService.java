package com.example.YBO.service;

import com.example.YBO.model.*;
import com.example.YBO.repository.*;
import com.example.YBO.status.RequestStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class JobRequestService {
    private final JobRequestRepository jobRequestRepository;
    private final CVRepository cvRepository;
    private final OpeningRepository openingRepository;
    private final AppUserRepository appUserRepository;

    //creates and saves new job request for than company to be capable of see it with its cv and opening
    public void sendJobRequest(UUID id) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CV cv = cvRepository.findByUserId(appUser.getId())
                .orElseThrow(() -> new RuntimeException("CV not found"));

        Opening opening = openingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opening not found"));

        JobRequest jobRequest = JobRequest.builder()
                        .cv(cv)
                        .opening(opening)
                        .requestStatus(RequestStatus.PENDING).build();

        jobRequestRepository.save(jobRequest);
    }

    public Page<JobRequest> getAllJobRequestsForCompany(UUID openingId, Pageable pageable) {
        return jobRequestRepository.findByOpeningId(openingId, pageable);
    }


    public void approveRequest(UUID requestId) {
        JobRequest jobRequest = jobRequestRepository.findById(requestId).get();
        jobRequest.setRequestStatus(RequestStatus.APPROVED);
        jobRequestRepository.save(jobRequest);
    }

    public void rejectRequest(UUID requestId) {
        JobRequest jobRequest = jobRequestRepository.findById(requestId).get();
        jobRequest.setRequestStatus(RequestStatus.REJECTED);
        jobRequestRepository.deleteById(requestId);
    }

    public Page<JobRequest> getAllApprovedRequestForUser(Pageable pageable) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CV cv = cvRepository.findByUserId(appUser.getId()).get();

        return jobRequestRepository.findByCvId(cv.getId(), pageable);
    }

}

package com.example.YBO.controller;

import com.example.YBO.model.JobRequest;
import com.example.YBO.model.Opening;
import com.example.YBO.service.JobRequestService;
import com.example.YBO.service.OpeningService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ybo/openings")
@AllArgsConstructor
public class OpeningController {

    private final OpeningService openingService;
    private final JobRequestService jobRequestService;

    @GetMapping
    public ResponseEntity<Page<Opening>> getAllOpenings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Opening> openings = openingService.getAllOpenings(PageRequest.of(page, size));
        return ResponseEntity.ok(openings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opening> getOpeningById(@PathVariable UUID id) {
        Opening opening = openingService.getOpeningById(id);
        return ResponseEntity.ok(opening);
    }

    @PostMapping("/{id}/apply")
    public ResponseEntity<String> applyToOpening(@PathVariable UUID id) {
        jobRequestService.sendJobRequest(id);
        return ResponseEntity.ok("Application submitted successfully!");
    }

    @GetMapping("/my-openings")
    public ResponseEntity<Page<Opening>> getMyCompanyOpenings(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<Opening> openings = openingService.getAllOpeningsAsCompany(PageRequest.of(page, size));
        return ResponseEntity.ok(openings);
    }

    @GetMapping("/{id}/job/requests")
    public ResponseEntity<Page<JobRequest>> getAllJobRequestsForCompany(@PathVariable UUID id, @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size) {
        Page<JobRequest> jobRequests = jobRequestService.getAllJobRequestsForCompany(id, PageRequest.of(page, size));
        return ResponseEntity.ok(jobRequests);
    }

    @PostMapping("/requests/{id}/approve")
    public void approveRequest(@PathVariable UUID id) {
        jobRequestService.approveRequest(id);
    }

    @PostMapping("/requests/{id}/reject")
    public void rejectRequest(@PathVariable UUID id) {
        jobRequestService.rejectRequest(id);
    }

}
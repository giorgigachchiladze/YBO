
package com.example.YBO.controller;

import com.example.YBO.dto.CVDto;
import com.example.YBO.model.JobRequest;
import com.example.YBO.model.Opening;
import com.example.YBO.service.CVService;
import com.example.YBO.service.JobRequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/ybo/user")
public class UserController {

    private final CVService cvService;
    private final JobRequestService jobRequestService;

    @PostMapping("/create/cv")
    public ResponseEntity<Void> saveCV(@Valid @RequestBody CVDto cvDto) {
        cvService.saveCV(cvDto);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(null);
    }

    @GetMapping("/approved/requests")
    public ResponseEntity<Page<JobRequest>> getMyCompanyOpenings(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Page<JobRequest> jobRequests = jobRequestService.getAllApprovedRequestForUser(PageRequest.of(page, size));
        return ResponseEntity.ok(jobRequests);
    }
}


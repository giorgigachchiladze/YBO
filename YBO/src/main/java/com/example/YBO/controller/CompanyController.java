package com.example.YBO.controller;


import com.example.YBO.dto.OpeningDto;
import com.example.YBO.service.OpeningService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/ybo/company")
public class CompanyController {

    private final OpeningService openingService;

    @PostMapping("/create/opening")
    public ResponseEntity<Void> saveCV(@Valid @RequestBody OpeningDto openingDto) {
        openingService.saveOpening(openingDto);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(null);
    }
}

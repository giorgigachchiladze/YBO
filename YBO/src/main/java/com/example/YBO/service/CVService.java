package com.example.YBO.service;

import com.example.YBO.dto.CVDto;
import com.example.YBO.model.AppUser;
import com.example.YBO.model.CV;
import com.example.YBO.repository.AppUserRepository;
import com.example.YBO.repository.CVRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@AllArgsConstructor
public class CVService {
    private final CVRepository cvRepository;
    private final AppUserRepository appUserRepository;

    //saves new cv type entity. it checks if user already have cv created and if its true it updates it.
    public void saveCV(CVDto cvDto) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));


        CV cv = cvRepository.findByUserId(appUser.getId())
                .orElseGet(() -> {
                    CV newCv = CV.builder().build();
                    newCv.setUser(appUser);
                    return newCv;
                });

        cv.setBirthday(cvDto.getBirthday());
        cv.setAddress(cvDto.getAddress());
        cv.setAboutMe(cvDto.getAboutMe());
        cv.setPhoneNumber(cvDto.getPhoneNumber());
        cv.setEducation(cvDto.getEducation());
        cv.setExperience(cvDto.getExperience());
        cv.setFirstName(cvDto.getFirstName());
        cv.setLastName(cvDto.getLastName());
        cv.setSkills(cvDto.getSkills());
        cv.setLanguages(cvDto.getLanguages());
        cv.setProfession(cvDto.getProfession());
        cv.setEmail(cvDto.getEmail());

        cvRepository.save(cv);
    }
}

package com.example.YBO.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//company name, phone number and address is filled by company when registering
//so it can be filled in OpeningService.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningDto {
    @NotBlank
    String profession;
    @NotBlank
    String salary;
    @NotBlank
    String schedule;
    @NotBlank
    String jobToDo;
    @NotBlank
    String experienceNeeded;
}

package com.example.YBO.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


//opening is filled by company and then joined with job request.

@Setter
@Getter
@Builder
@Entity
@Table(name = "opening")
@NoArgsConstructor
@AllArgsConstructor
public class Opening {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "profession")
    private String profession;

    @Column(name = "salary")
    private String salary;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "job_to_do",  columnDefinition = "TEXT")
    private String jobToDo;

    @Column(name = "experience_needed")
    private String experienceNeeded;


}

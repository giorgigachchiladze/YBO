package com.example.YBO.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;
//CV is an entity that is connected to our user entity by OneToOne relationship.
//it has standard CV columns that you use in job interview.


@Entity
@Table(name = "CV")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CV {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private Instant birthday;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "profession")
    private String profession;

    @Column(name = "about_me", columnDefinition = "TEXT")
    private String aboutMe;

    @Column(name = "experience", columnDefinition = "TEXT")
    private String experience;

    @Column(name = "education", columnDefinition = "TEXT")
    private String education;

    @Column(name = "skills", columnDefinition = "TEXT")
    private String skills;

    @Column(name = "languages", columnDefinition = "TEXT")
    private String languages;

}

package com.example.YBO.model;

import com.example.YBO.roles.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

//company is filled in registration and after logging in they can have multiple openings
//so it joins with opening entity by ManyToOne relationship.


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "company")
public class Company implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String companyName;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "company_description", columnDefinition = "TEXT")
    private String companyDescription;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }
}

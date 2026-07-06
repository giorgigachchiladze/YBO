package com.example.YBO.security;

import com.example.YBO.security.JwtAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login", "/login.html").permitAll()
                        .requestMatchers("/register", "/register.html").permitAll()
                        .requestMatchers("/register/company", "/registerCompany.html").permitAll()
                        .requestMatchers("/create/cv", "/CV.html").permitAll()
                        .requestMatchers("/post/opening", "/setOpening.html").permitAll()
                        .requestMatchers("/get/openings", "/getOpening.html").permitAll()
                        .requestMatchers("/user/home", "/userHome.html").permitAll()
                        .requestMatchers("/company/home", "/companyHome.html").permitAll()
                        .requestMatchers("/ybo", "/YBO.html").permitAll()
                        .requestMatchers("/get/approved/requests", "/getApprovedRequestsForUser.html").permitAll()
                        .requestMatchers("/get/company/openings", "/getOpeningAsCompany.html").permitAll()
                        .requestMatchers("/favicon.ico", "/error").permitAll()
                        .requestMatchers("/ybo/auth/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/ybo/openings/my-openings").hasAuthority("COMPANY")
                        .requestMatchers(HttpMethod.GET, "/ybo/user/approved/requests").hasAuthority("USER")

                        .requestMatchers(HttpMethod.GET, "/ybo/openings", "/ybo/openings/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/ybo/openings/*/apply").hasAuthority("USER")
                        .requestMatchers("/ybo/user/create/cv/**").hasAuthority("USER")
                        .requestMatchers("/ybo/company/create/opening/**").hasAuthority("COMPANY")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}

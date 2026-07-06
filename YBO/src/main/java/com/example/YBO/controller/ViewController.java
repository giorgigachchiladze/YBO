package com.example.YBO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "forward:/login.html";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "forward:/register.html";
    }

    @GetMapping("/register/company")
    public String getRegisterCompanyPage() {
        return "forward:/registerCompany.html";
    }

    @GetMapping("/create/cv")
    public String getSaveCVPage() {
        return "forward:/CV.html";
    }

    @GetMapping("/post/opening")
    public String getPostOpeningPage() {
        return "forward:/setOpening.html";
    }

    @GetMapping("/get/openings")
    public String getOpeningPage() {
        return "forward:/getOpening.html";
    }

    @GetMapping("/get/company/openings")
    public String getOpeningPageAsCompany() {
        return "forward:/getOpeningAsCompany.html";
    }

    @GetMapping("/get/approved/requests")
    public String getApprovedRequests() {
        return "forward:/getApprovedRequestsForUser.html";
    }

    @GetMapping("/user/home")
    public String getUserHomePage() {
        return "forward:/userHome.html";
    }

    @GetMapping("/company/home")
    public String getCompanyHomePage() {
        return "forward:/companyHome.html";
    }

    @GetMapping("/ybo")
    public String getMainPage() {
        return "forward:/YBO.html";
    }
}
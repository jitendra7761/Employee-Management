package com.example.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.service.EmailService;

import com.example.Employee.service.OtpServiceMail;
@RestController
@RequestMapping("/api")
public class OtpMailController {

	@Autowired
    private OtpServiceMail otpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/generate/{email}")
    public String generateOtp(@PathVariable String email) {
        String otp = otpService.generateOtp();
        emailService.sendOtpEmail(email, otp);
        return "OTP sent to your email.";
    }
}

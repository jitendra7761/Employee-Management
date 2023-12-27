package com.example.Employee.service;


import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceMail {
	public String generateOtp() {
        // Generate a 6-digit random OTP
        return RandomStringUtils.randomNumeric(6);
    }
}

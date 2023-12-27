package com.example.Employee.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Employee.payload.TimingInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimingInterceptor());
    }
}

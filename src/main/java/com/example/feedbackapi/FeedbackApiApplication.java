package com.example.feedbackapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example.feedbackapi")
@EntityScan(basePackages = "com.example.feedbackapi.entity")
public class FeedbackApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeedbackApiApplication.class, args);
    }
}

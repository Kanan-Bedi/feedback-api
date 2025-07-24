package com.example.feedbackapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedbackapi.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

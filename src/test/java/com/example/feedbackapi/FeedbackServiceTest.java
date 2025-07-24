package com.example.feedbackapi;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.feedbackapi.entity.Feedback;
import com.example.feedbackapi.repository.FeedbackRepository;
import com.example.feedbackapi.service.FeedbackService;

public class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFeedback() {
        Feedback feedback = new Feedback();
        feedback.setName("Service Test");
        feedback.setEmail("service@example.com");
        feedback.setMessage("Testing service layer save.");

        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Feedback saved = feedbackService.saveFeedback(feedback);

        assertThat(saved.getName()).isEqualTo("Service Test");
        verify(feedbackRepository, times(1)).save(feedback);
    }

    @Test
    void testGetAllFeedback() {
        Feedback f1 = new Feedback();
        f1.setName("A");

        Feedback f2 = new Feedback();
        f2.setName("B");

        when(feedbackRepository.findAll()).thenReturn(Arrays.asList(f1, f2));

        List<Feedback> result = feedbackService.getAllFeedback();

        assertThat(result).hasSize(2);
        verify(feedbackRepository, times(1)).findAll();
    }
}

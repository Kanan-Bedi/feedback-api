package com.example.feedbackapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.feedbackapi.entity.Feedback;
import com.example.feedbackapi.repository.FeedbackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        feedbackRepository.deleteAll();
    }

    @Test
    void testNameTooShort() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setName("Jo"); // Too short
        feedback.setEmail("valid@example.com");
        feedback.setMessage("This is a valid message longer than 10 characters.");

        mockMvc.perform(post("/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMessageTooShort() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setName("Valid Name");
        feedback.setEmail("valid@example.com");
        feedback.setMessage("Short"); // Too short

        mockMvc.perform(post("/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testNameIsBlank() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setName(""); // Blank
        feedback.setEmail("valid@example.com");
        feedback.setMessage("This is a valid message longer than 10 characters.");

        mockMvc.perform(post("/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMessageIsBlank() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setName("Valid Name");
        feedback.setEmail("valid@example.com");
        feedback.setMessage(""); // Blank

        mockMvc.perform(post("/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testInvalidEmailFormat() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setName("Valid Name");
        feedback.setEmail("invalid-email"); // Invalid format
        feedback.setMessage("This is a valid message longer than 10 characters.");

        mockMvc.perform(post("/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isBadRequest());
    }
}

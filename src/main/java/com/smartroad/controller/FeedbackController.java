package com.smartroad.controller;

import com.smartroad.dto.FeedbackRequest;
import com.smartroad.dto.FeedbackResponse;
import com.smartroad.service.LearningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final LearningService learningService;

    public FeedbackController(LearningService learningService) {
        this.learningService = learningService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponse> submitFeedback(@RequestBody FeedbackRequest request) {
        FeedbackResponse response = learningService.applyFeedback(request);
        return ResponseEntity.ok(response);
    }
}

package com.smartroad.controller;

import com.smartroad.dto.FeedbackRequest;
import com.smartroad.dto.FeedbackResponse;
import com.smartroad.service.LearningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
@Tag(name = "Feedback", description = "Collect user reactions to proposed routes for continuous learning.")
public class FeedbackController {

    private final LearningService learningService;

    public FeedbackController(LearningService learningService) {
        this.learningService = learningService;
    }

    @PostMapping
    @Operation(
            summary = "Submit route feedback",
            description = "Captures user feedback on a base route vs. a proposed route and updates learning models."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feedback processed",
                    content = @Content(schema = @Schema(implementation = FeedbackResponse.class)))
    })
    public ResponseEntity<FeedbackResponse> submitFeedback(@RequestBody FeedbackRequest request) {
        FeedbackResponse response = learningService.applyFeedback(request);
        return ResponseEntity.ok(response);
    }
}

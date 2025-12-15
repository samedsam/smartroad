package com.exitsmart.controller;

import com.exitsmart.domain.UserProfile;
import com.exitsmart.domain.UserReaction;
import com.exitsmart.dto.FeedbackRequest;
import com.exitsmart.dto.FeedbackResponse;
import com.exitsmart.service.LearningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final LearningService learningService;

    public FeedbackController(LearningService learningService) {
        this.learningService = learningService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponse> submitFeedback(@Valid @RequestBody FeedbackRequest request) {
        UserReaction reaction = parseReaction(request.getReaction());
        UserProfile updatedProfile = learningService.applyFeedback(request.getUserProfile(), request.getBaseRoute(),
                request.getProposedRoute(), reaction);
        String message = buildMessage(reaction, request.getBaseRoute(), request.getProposedRoute());
        return ResponseEntity.ok(new FeedbackResponse(updatedProfile, message));
    }

    private UserReaction parseReaction(String reaction) {
        try {
            return UserReaction.valueOf(reaction == null ? "" : reaction.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid reaction value");
        }
    }

    private String buildMessage(UserReaction reaction, com.exitsmart.dto.RouteSummaryDto baseRoute,
                                com.exitsmart.dto.RouteSummaryDto proposedRoute) {
        return switch (reaction) {
            case ACCEPTED -> "Profile adjusted to reward similar detours.";
            case IGNORED -> "Preference nudged toward faster, simpler routes.";
            case ABANDONED -> "Detours penalized to avoid future frustration.";
        };
    }
}

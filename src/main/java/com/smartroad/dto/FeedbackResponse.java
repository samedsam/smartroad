package com.smartroad.dto;

import com.smartroad.domain.UserProfile;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Result of processing user feedback, including any updated user preferences.")
public class FeedbackResponse {

    @Schema(description = "User profile after feedback was applied.")
    private UserProfile updatedUserProfile;

    @Schema(description = "Status message summarizing the feedback outcome.", example = "Feedback applied")
    private String message;

    public FeedbackResponse() {
    }

    public FeedbackResponse(UserProfile updatedUserProfile, String message) {
        this.updatedUserProfile = updatedUserProfile;
        this.message = message;
    }

    public UserProfile getUpdatedUserProfile() {
        return updatedUserProfile;
    }

    public void setUpdatedUserProfile(UserProfile updatedUserProfile) {
        this.updatedUserProfile = updatedUserProfile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

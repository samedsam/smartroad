package com.exitsmart.dto;

import com.exitsmart.domain.UserProfile;

public class FeedbackResponse {

    private UserProfile updatedUserProfile;
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

package com.exitsmart.dto;

import com.exitsmart.domain.UserProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FeedbackRequest {

    @NotNull
    private UserProfile userProfile;

    @NotNull
    private RouteSummaryDto baseRoute;

    @NotNull
    private RouteSummaryDto proposedRoute;

    @NotBlank
    private String reaction;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public RouteSummaryDto getBaseRoute() {
        return baseRoute;
    }

    public void setBaseRoute(RouteSummaryDto baseRoute) {
        this.baseRoute = baseRoute;
    }

    public RouteSummaryDto getProposedRoute() {
        return proposedRoute;
    }

    public void setProposedRoute(RouteSummaryDto proposedRoute) {
        this.proposedRoute = proposedRoute;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }
}

package com.smartroad.dto;

import com.smartroad.domain.UserProfile;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User feedback comparing a base route and a proposed alternative.")
public class FeedbackRequest {

    @Schema(description = "User profile context when the feedback was given.")
    private UserProfile userProfile;

    @Schema(description = "Summary of the original route followed by the user.")
    private RouteSummaryDto baseRoute;

    @Schema(description = "Summary of the optimized or proposed route.")
    private RouteSummaryDto proposedRoute;

    @Schema(description = "User reaction to the proposal (e.g., LIKE, DISLIKE, COMMENT).", example = "LIKE")
    private String reaction;

    public FeedbackRequest() {
    }

    public FeedbackRequest(UserProfile userProfile, RouteSummaryDto baseRoute, RouteSummaryDto proposedRoute, String reaction) {
        this.userProfile = userProfile;
        this.baseRoute = baseRoute;
        this.proposedRoute = proposedRoute;
        this.reaction = reaction;
    }

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

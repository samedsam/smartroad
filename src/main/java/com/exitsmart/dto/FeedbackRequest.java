package com.exitsmart.dto;

import com.exitsmart.domain.UserProfile;

public class FeedbackRequest {

    private UserProfile userProfile;
    private RouteSummaryDto baseRoute;
    private RouteSummaryDto proposedRoute;
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

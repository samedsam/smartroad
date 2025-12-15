package com.exitsmart.dto;

import com.exitsmart.domain.Route;
import com.exitsmart.domain.UserProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OptimizationRequest {

    @NotBlank
    private String userId;

    @NotNull
    private Route baseHighwayRoute;

    @NotNull
    private List<Route> candidateRoutes;

    private UserProfile userProfile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Route getBaseHighwayRoute() {
        return baseHighwayRoute;
    }

    public void setBaseHighwayRoute(Route baseHighwayRoute) {
        this.baseHighwayRoute = baseHighwayRoute;
    }

    public List<Route> getCandidateRoutes() {
        return candidateRoutes;
    }

    public void setCandidateRoutes(List<Route> candidateRoutes) {
        this.candidateRoutes = candidateRoutes;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}

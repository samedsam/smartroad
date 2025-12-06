package com.smartroad.domain;

import java.util.Collections;
import java.util.List;

public class OptimizationContext {

    private Route baseHighwayRoute;
    private List<Route> candidateRoutes;
    private UserProfile userProfile;
    private String userId;

    public OptimizationContext() {
    }

    public OptimizationContext(Route baseHighwayRoute, List<Route> candidateRoutes, UserProfile userProfile, String userId) {
        this.baseHighwayRoute = baseHighwayRoute;
        this.candidateRoutes = candidateRoutes;
        this.userProfile = userProfile;
        this.userId = userId;
    }

    public Route getBaseHighwayRoute() {
        return baseHighwayRoute;
    }

    public void setBaseHighwayRoute(Route baseHighwayRoute) {
        this.baseHighwayRoute = baseHighwayRoute;
    }

    public List<Route> getCandidateRoutes() {
        return candidateRoutes == null ? Collections.emptyList() : candidateRoutes;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

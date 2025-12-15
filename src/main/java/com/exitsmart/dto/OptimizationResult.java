package com.exitsmart.dto;

import com.exitsmart.domain.UserProfile;

import java.util.List;

public class OptimizationResult {

    private ScoredRoute bestRoute;
    private List<ScoredRoute> rankedRoutes;
    private UserProfile updatedProfile;

    public OptimizationResult() {
    }

    public OptimizationResult(ScoredRoute bestRoute, List<ScoredRoute> rankedRoutes, UserProfile updatedProfile) {
        this.bestRoute = bestRoute;
        this.rankedRoutes = rankedRoutes;
        this.updatedProfile = updatedProfile;
    }

    public ScoredRoute getBestRoute() {
        return bestRoute;
    }

    public void setBestRoute(ScoredRoute bestRoute) {
        this.bestRoute = bestRoute;
    }

    public List<ScoredRoute> getRankedRoutes() {
        return rankedRoutes;
    }

    public void setRankedRoutes(List<ScoredRoute> rankedRoutes) {
        this.rankedRoutes = rankedRoutes;
    }

    public UserProfile getUpdatedProfile() {
        return updatedProfile;
    }

    public void setUpdatedProfile(UserProfile updatedProfile) {
        this.updatedProfile = updatedProfile;
    }
}

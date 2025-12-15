package com.smartroad.dto;

import com.smartroad.domain.UserProfile;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Result of a route optimization request, including ranked routes and updated profile.")
public class OptimizationResult {

    @Schema(description = "The top-ranked route chosen for the user.")
    private ScoredRoute bestRoute;

    @Schema(description = "All candidate routes ranked by score.")
    private List<ScoredRoute> rankedRoutes;

    @Schema(description = "User profile after applying learning from this optimization.")
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

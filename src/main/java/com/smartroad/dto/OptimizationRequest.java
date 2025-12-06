package com.smartroad.dto;

import com.smartroad.domain.Route;
import com.smartroad.domain.UserProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Request payload for route optimization.")
public class OptimizationRequest {

    @NotBlank
    @Schema(description = "Identifier of the user requesting optimization.", example = "user-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @NotNull
    @Schema(description = "Current base highway route used as a reference.", requiredMode = Schema.RequiredMode.REQUIRED)
    private Route baseHighwayRoute;

    @NotNull
    @Schema(description = "Candidate alternative routes to score.", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Route> candidateRoutes;

    @Schema(description = "Optional user profile overrides to influence scoring.")
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

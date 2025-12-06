package com.smartroad.dto;

import com.smartroad.domain.UserProfile;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Input payload for running savings simulations across demo scenarios.")
public class SavingsSimulationRequest {

    @Schema(description = "List of scenarios to simulate with expected yearly trip counts.", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<SimulationScenarioInput> scenarios;

    @Schema(description = "Optional user profile used to personalize ROI analysis.")
    private UserProfile userProfile;

    @Schema(description = "Subscription price per year to evaluate ROI against.", example = "59.99")
    private Double subscriptionPricePerYear;

    public SavingsSimulationRequest() {
    }

    public SavingsSimulationRequest(List<SimulationScenarioInput> scenarios, UserProfile userProfile,
                                    Double subscriptionPricePerYear) {
        this.scenarios = scenarios;
        this.userProfile = userProfile;
        this.subscriptionPricePerYear = subscriptionPricePerYear;
    }

    public List<SimulationScenarioInput> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<SimulationScenarioInput> scenarios) {
        this.scenarios = scenarios;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Double getSubscriptionPricePerYear() {
        return subscriptionPricePerYear;
    }

    public void setSubscriptionPricePerYear(Double subscriptionPricePerYear) {
        this.subscriptionPricePerYear = subscriptionPricePerYear;
    }
}

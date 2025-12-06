package com.smartroad.dto;

import com.smartroad.domain.UserProfile;

import java.util.List;

public class SavingsSimulationRequest {

    private List<SimulationScenarioInput> scenarios;
    private UserProfile userProfile;
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

package com.smartroad.dto;

import java.util.List;

public class SavingsSimulationResponse {

    private List<ScenarioSimulationResult> scenarioResults;
    private double totalYearlySavings;
    private double totalYearlyExtraMinutes;
    private Double subscriptionPricePerYear;
    private Double netGainAfterSubscription;
    private Double roiPercentage;
    private String roiCategory;
    private String roiExplanation;

    public SavingsSimulationResponse() {
    }

    public SavingsSimulationResponse(List<ScenarioSimulationResult> scenarioResults, double totalYearlySavings,
                                     double totalYearlyExtraMinutes, Double subscriptionPricePerYear,
                                     Double netGainAfterSubscription, Double roiPercentage, String roiCategory,
                                     String roiExplanation) {
        this.scenarioResults = scenarioResults;
        this.totalYearlySavings = totalYearlySavings;
        this.totalYearlyExtraMinutes = totalYearlyExtraMinutes;
        this.subscriptionPricePerYear = subscriptionPricePerYear;
        this.netGainAfterSubscription = netGainAfterSubscription;
        this.roiPercentage = roiPercentage;
        this.roiCategory = roiCategory;
        this.roiExplanation = roiExplanation;
    }

    public List<ScenarioSimulationResult> getScenarioResults() {
        return scenarioResults;
    }

    public void setScenarioResults(List<ScenarioSimulationResult> scenarioResults) {
        this.scenarioResults = scenarioResults;
    }

    public double getTotalYearlySavings() {
        return totalYearlySavings;
    }

    public void setTotalYearlySavings(double totalYearlySavings) {
        this.totalYearlySavings = totalYearlySavings;
    }

    public double getTotalYearlyExtraMinutes() {
        return totalYearlyExtraMinutes;
    }

    public void setTotalYearlyExtraMinutes(double totalYearlyExtraMinutes) {
        this.totalYearlyExtraMinutes = totalYearlyExtraMinutes;
    }

    public Double getSubscriptionPricePerYear() {
        return subscriptionPricePerYear;
    }

    public void setSubscriptionPricePerYear(Double subscriptionPricePerYear) {
        this.subscriptionPricePerYear = subscriptionPricePerYear;
    }

    public Double getNetGainAfterSubscription() {
        return netGainAfterSubscription;
    }

    public void setNetGainAfterSubscription(Double netGainAfterSubscription) {
        this.netGainAfterSubscription = netGainAfterSubscription;
    }

    public Double getRoiPercentage() {
        return roiPercentage;
    }

    public void setRoiPercentage(Double roiPercentage) {
        this.roiPercentage = roiPercentage;
    }

    public String getRoiCategory() {
        return roiCategory;
    }

    public void setRoiCategory(String roiCategory) {
        this.roiCategory = roiCategory;
    }

    public String getRoiExplanation() {
        return roiExplanation;
    }

    public void setRoiExplanation(String roiExplanation) {
        this.roiExplanation = roiExplanation;
    }
}

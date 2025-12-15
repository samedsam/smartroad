package com.smartroad.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Aggregated simulation results summarizing savings, time impact, and ROI.")
public class SavingsSimulationResponse {

    @Schema(description = "Results for each simulated scenario.")
    private List<ScenarioSimulationResult> scenarioResults;

    @Schema(description = "Total estimated yearly savings across all scenarios.", example = "120.5")
    private double totalYearlySavings;

    @Schema(description = "Total additional time across all scenarios in minutes.", example = "45")
    private double totalYearlyExtraMinutes;

    @Schema(description = "Subscription price used for ROI calculations.", example = "59.99")
    private Double subscriptionPricePerYear;

    @Schema(description = "Net yearly gain after subtracting the subscription price.", example = "60.51")
    private Double netGainAfterSubscription;

    @Schema(description = "Return on investment percentage.", example = "101.7")
    private Double roiPercentage;

    @Schema(description = "Categorized ROI label (e.g., HIGH, MEDIUM, LOW).", example = "HIGH")
    private String roiCategory;

    @Schema(description = "Narrative explanation of the ROI result.")
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

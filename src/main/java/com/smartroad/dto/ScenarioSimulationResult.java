package com.smartroad.dto;

import com.smartroad.domain.DealType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Per-scenario simulation result with savings and time impact.")
public class ScenarioSimulationResult {

    @Schema(description = "Identifier of the simulated scenario.", example = "lyon-paris-weekend")
    private String scenarioId;

    @Schema(description = "Expected number of trips per year for this scenario.", example = "12")
    private int tripsPerYear;

    @Schema(description = "Estimated savings per trip in the local currency.", example = "4.5")
    private double savingsPerTrip;

    @Schema(description = "Additional minutes per trip compared to baseline.", example = "3")
    private double extraMinutesPerTrip;

    @Schema(description = "Total estimated savings per year for this scenario.", example = "54")
    private double yearlySavings;

    @Schema(description = "Total additional minutes per year for this scenario.", example = "36")
    private double yearlyExtraMinutes;

    @Schema(description = "Deal classification derived from the score.")
    private DealType dealType;

    @Schema(description = "Explanation of why the scenario fits the deal classification.")
    private String dealExplanation;

    public ScenarioSimulationResult() {
    }

    public ScenarioSimulationResult(String scenarioId, int tripsPerYear, double savingsPerTrip, double extraMinutesPerTrip,
                                    double yearlySavings, double yearlyExtraMinutes, DealType dealType,
                                    String dealExplanation) {
        this.scenarioId = scenarioId;
        this.tripsPerYear = tripsPerYear;
        this.savingsPerTrip = savingsPerTrip;
        this.extraMinutesPerTrip = extraMinutesPerTrip;
        this.yearlySavings = yearlySavings;
        this.yearlyExtraMinutes = yearlyExtraMinutes;
        this.dealType = dealType;
        this.dealExplanation = dealExplanation;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public int getTripsPerYear() {
        return tripsPerYear;
    }

    public void setTripsPerYear(int tripsPerYear) {
        this.tripsPerYear = tripsPerYear;
    }

    public double getSavingsPerTrip() {
        return savingsPerTrip;
    }

    public void setSavingsPerTrip(double savingsPerTrip) {
        this.savingsPerTrip = savingsPerTrip;
    }

    public double getExtraMinutesPerTrip() {
        return extraMinutesPerTrip;
    }

    public void setExtraMinutesPerTrip(double extraMinutesPerTrip) {
        this.extraMinutesPerTrip = extraMinutesPerTrip;
    }

    public double getYearlySavings() {
        return yearlySavings;
    }

    public void setYearlySavings(double yearlySavings) {
        this.yearlySavings = yearlySavings;
    }

    public double getYearlyExtraMinutes() {
        return yearlyExtraMinutes;
    }

    public void setYearlyExtraMinutes(double yearlyExtraMinutes) {
        this.yearlyExtraMinutes = yearlyExtraMinutes;
    }

    public DealType getDealType() {
        return dealType;
    }

    public void setDealType(DealType dealType) {
        this.dealType = dealType;
    }

    public String getDealExplanation() {
        return dealExplanation;
    }

    public void setDealExplanation(String dealExplanation) {
        this.dealExplanation = dealExplanation;
    }
}

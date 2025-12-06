package com.smartroad.dto;

import com.smartroad.domain.DealType;

public class ScenarioSimulationResult {

    private String scenarioId;
    private int tripsPerYear;
    private double savingsPerTrip;
    private double extraMinutesPerTrip;
    private double yearlySavings;
    private double yearlyExtraMinutes;
    private DealType dealType;
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

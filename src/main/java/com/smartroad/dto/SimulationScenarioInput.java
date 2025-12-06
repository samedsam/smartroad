package com.smartroad.dto;

public class SimulationScenarioInput {

    private String scenarioId;
    private int tripsPerYear;

    public SimulationScenarioInput() {
    }

    public SimulationScenarioInput(String scenarioId, int tripsPerYear) {
        this.scenarioId = scenarioId;
        this.tripsPerYear = tripsPerYear;
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
}

package com.smartroad.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Input parameters for simulating a single demo scenario.")
public class SimulationScenarioInput {

    @Schema(description = "Identifier of the demo scenario to run.", example = "paris-lyon-daily")
    private String scenarioId;

    @Schema(description = "Expected number of trips per year for this scenario.", example = "24")
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

package com.smartroad.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detailed scoring components for a route.")
public class ScoreBreakdown {

    @Schema(description = "Difference in toll cost compared to the base route.", example = "-3.5")
    private double deltaCost;

    @Schema(description = "Difference in travel time in minutes compared to the base route.", example = "4.2")
    private double deltaMinutes;

    @Schema(description = "Additional distance in kilometers introduced by the detour.", example = "1.8")
    private double detourKm;

    @Schema(description = "Penalty applied for increased travel time.", example = "2.0")
    private double timePenalty;

    @Schema(description = "Penalty applied for route complexity (e.g., number of exits).", example = "1.5")
    private double complexityPenalty;

    @Schema(description = "Raw score before normalization.", example = "78.0")
    private double rawScore;

    public ScoreBreakdown() {
    }

    public ScoreBreakdown(double deltaCost, double deltaMinutes, double detourKm, double timePenalty,
                          double complexityPenalty, double rawScore) {
        this.deltaCost = deltaCost;
        this.deltaMinutes = deltaMinutes;
        this.detourKm = detourKm;
        this.timePenalty = timePenalty;
        this.complexityPenalty = complexityPenalty;
        this.rawScore = rawScore;
    }

    public double getDeltaCost() {
        return deltaCost;
    }

    public void setDeltaCost(double deltaCost) {
        this.deltaCost = deltaCost;
    }

    public double getDeltaMinutes() {
        return deltaMinutes;
    }

    public void setDeltaMinutes(double deltaMinutes) {
        this.deltaMinutes = deltaMinutes;
    }

    public double getDetourKm() {
        return detourKm;
    }

    public void setDetourKm(double detourKm) {
        this.detourKm = detourKm;
    }

    public double getTimePenalty() {
        return timePenalty;
    }

    public void setTimePenalty(double timePenalty) {
        this.timePenalty = timePenalty;
    }

    public double getComplexityPenalty() {
        return complexityPenalty;
    }

    public void setComplexityPenalty(double complexityPenalty) {
        this.complexityPenalty = complexityPenalty;
    }

    public double getRawScore() {
        return rawScore;
    }

    public void setRawScore(double rawScore) {
        this.rawScore = rawScore;
    }
}

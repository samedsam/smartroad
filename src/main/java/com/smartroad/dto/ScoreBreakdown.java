package com.smartroad.dto;

public class ScoreBreakdown {

    private double deltaCost;
    private double deltaMinutes;
    private double detourKm;
    private double timePenalty;
    private double complexityPenalty;
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

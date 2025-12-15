package com.smartroad.dto;

import com.smartroad.domain.Route;
import com.smartroad.domain.DealType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A route annotated with scoring and deal classification information.")
public class ScoredRoute {

    @Schema(description = "Route geometry and metadata.")
    private Route route;

    @Schema(description = "Final score assigned to the route.", example = "82.5")
    private double score;

    @Schema(description = "Detailed scoring components contributing to the final score.")
    private ScoreBreakdown breakdown;

    @Schema(description = "Deal classification for the route.")
    private DealType dealType;

    @Schema(description = "Human-friendly explanation of the score.")
    private String explanation;

    public ScoredRoute() {
    }

    public ScoredRoute(Route route, double score, ScoreBreakdown breakdown, DealType dealType, String explanation) {
        this.route = route;
        this.score = score;
        this.breakdown = breakdown;
        this.dealType = dealType;
        this.explanation = explanation;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ScoreBreakdown getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(ScoreBreakdown breakdown) {
        this.breakdown = breakdown;
    }

    public DealType getDealType() {
        return dealType;
    }

    public void setDealType(DealType dealType) {
        this.dealType = dealType;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}

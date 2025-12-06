package com.smartroad.dto;

import com.smartroad.domain.Route;
import com.smartroad.domain.DealType;

public class ScoredRoute {

    private Route route;
    private double score;
    private ScoreBreakdown breakdown;
    private DealType dealType;
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

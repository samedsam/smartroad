package com.exitsmart.dto;

import com.exitsmart.domain.Route;

public class ScoredRoute {

    private Route route;
    private double score;
    private double tollSavingsComparedToBase;
    private double extraMinutesComparedToBase;
    private double detourKmComparedToBase;

    public ScoredRoute() {
    }

    public ScoredRoute(Route route, double score, double tollSavingsComparedToBase, double extraMinutesComparedToBase,
                       double detourKmComparedToBase) {
        this.route = route;
        this.score = score;
        this.tollSavingsComparedToBase = tollSavingsComparedToBase;
        this.extraMinutesComparedToBase = extraMinutesComparedToBase;
        this.detourKmComparedToBase = detourKmComparedToBase;
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

    public double getTollSavingsComparedToBase() {
        return tollSavingsComparedToBase;
    }

    public void setTollSavingsComparedToBase(double tollSavingsComparedToBase) {
        this.tollSavingsComparedToBase = tollSavingsComparedToBase;
    }

    public double getExtraMinutesComparedToBase() {
        return extraMinutesComparedToBase;
    }

    public void setExtraMinutesComparedToBase(double extraMinutesComparedToBase) {
        this.extraMinutesComparedToBase = extraMinutesComparedToBase;
    }

    public double getDetourKmComparedToBase() {
        return detourKmComparedToBase;
    }

    public void setDetourKmComparedToBase(double detourKmComparedToBase) {
        this.detourKmComparedToBase = detourKmComparedToBase;
    }
}

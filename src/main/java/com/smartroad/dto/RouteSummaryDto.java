package com.smartroad.dto;

public class RouteSummaryDto {

    private double totalDistanceKm;
    private double totalMinutes;
    private double totalTollCost;
    private int numberOfHighwayExits;

    public RouteSummaryDto() {
    }

    public RouteSummaryDto(double totalDistanceKm, double totalMinutes, double totalTollCost, int numberOfHighwayExits) {
        this.totalDistanceKm = totalDistanceKm;
        this.totalMinutes = totalMinutes;
        this.totalTollCost = totalTollCost;
        this.numberOfHighwayExits = numberOfHighwayExits;
    }

    public double getTotalDistanceKm() {
        return totalDistanceKm;
    }

    public void setTotalDistanceKm(double totalDistanceKm) {
        this.totalDistanceKm = totalDistanceKm;
    }

    public double getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(double totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public double getTotalTollCost() {
        return totalTollCost;
    }

    public void setTotalTollCost(double totalTollCost) {
        this.totalTollCost = totalTollCost;
    }

    public int getNumberOfHighwayExits() {
        return numberOfHighwayExits;
    }

    public void setNumberOfHighwayExits(int numberOfHighwayExits) {
        this.numberOfHighwayExits = numberOfHighwayExits;
    }
}

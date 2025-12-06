package com.smartroad.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Compact summary of a route for display and comparison.")
public class RouteSummaryDto {

    @Schema(description = "Total distance of the route in kilometers.", example = "125.7")
    private double totalDistanceKm;

    @Schema(description = "Total travel time in minutes.", example = "98")
    private double totalMinutes;

    @Schema(description = "Total toll cost for the route in the local currency.", example = "15.5")
    private double totalTollCost;

    @Schema(description = "Number of highway exits involved in the trip.", example = "4")
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

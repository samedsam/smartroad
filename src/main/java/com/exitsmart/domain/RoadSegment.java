package com.exitsmart.domain;

public class RoadSegment {

    private String id;
    private Location from;
    private Location to;
    private double distanceKm;
    private double estimatedMinutes;
    private double tollCost;
    private RoadType roadType;

    public RoadSegment() {
    }

    public RoadSegment(String id, Location from, Location to, double distanceKm, double estimatedMinutes, double tollCost, RoadType roadType) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.distanceKm = distanceKm;
        this.estimatedMinutes = estimatedMinutes;
        this.tollCost = tollCost;
        this.roadType = roadType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public double getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public void setEstimatedMinutes(double estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public double getTollCost() {
        return tollCost;
    }

    public void setTollCost(double tollCost) {
        this.tollCost = tollCost;
    }

    public RoadType getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }
}

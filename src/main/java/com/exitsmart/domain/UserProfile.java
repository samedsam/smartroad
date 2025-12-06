package com.exitsmart.domain;

public class UserProfile {

    private double valueOfTimeEuroPerHour;
    private int maxExtraMinutes;
    private double maxDetourKm;
    private double complexityFactor;
    private boolean preferHighways;
    private double tollSensitivity;

    public UserProfile() {
    }

    public UserProfile(double valueOfTimeEuroPerHour, int maxExtraMinutes, double maxDetourKm, double complexityFactor,
                       boolean preferHighways, double tollSensitivity) {
        this.valueOfTimeEuroPerHour = valueOfTimeEuroPerHour;
        this.maxExtraMinutes = maxExtraMinutes;
        this.maxDetourKm = maxDetourKm;
        this.complexityFactor = complexityFactor;
        this.preferHighways = preferHighways;
        this.tollSensitivity = tollSensitivity;
    }

    public double getValueOfTimeEuroPerHour() {
        return valueOfTimeEuroPerHour;
    }

    public void setValueOfTimeEuroPerHour(double valueOfTimeEuroPerHour) {
        this.valueOfTimeEuroPerHour = valueOfTimeEuroPerHour;
    }

    public int getMaxExtraMinutes() {
        return maxExtraMinutes;
    }

    public void setMaxExtraMinutes(int maxExtraMinutes) {
        this.maxExtraMinutes = maxExtraMinutes;
    }

    public double getMaxDetourKm() {
        return maxDetourKm;
    }

    public void setMaxDetourKm(double maxDetourKm) {
        this.maxDetourKm = maxDetourKm;
    }

    public double getComplexityFactor() {
        return complexityFactor;
    }

    public void setComplexityFactor(double complexityFactor) {
        this.complexityFactor = complexityFactor;
    }

    public boolean isPreferHighways() {
        return preferHighways;
    }

    public void setPreferHighways(boolean preferHighways) {
        this.preferHighways = preferHighways;
    }

    public double getTollSensitivity() {
        return tollSensitivity;
    }

    public void setTollSensitivity(double tollSensitivity) {
        this.tollSensitivity = tollSensitivity;
    }
}

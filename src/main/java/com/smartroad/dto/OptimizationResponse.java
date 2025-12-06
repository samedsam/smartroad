package com.smartroad.dto;

public class OptimizationResponse {

    private String userId;
    private OptimizationResult result;

    public OptimizationResponse() {
    }

    public OptimizationResponse(String userId, OptimizationResult result) {
        this.userId = userId;
        this.result = result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OptimizationResult getResult() {
        return result;
    }

    public void setResult(OptimizationResult result) {
        this.result = result;
    }
}

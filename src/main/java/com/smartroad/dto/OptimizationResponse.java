package com.smartroad.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Optimization response with the best route and scoring summary.")
public class OptimizationResponse {

    @Schema(description = "Identifier of the user that requested the optimization.", example = "user-123")
    private String userId;

    @Schema(description = "Computed optimization result including ranked routes and updated profile.")
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

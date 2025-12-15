package com.smartroad.controller;

import com.smartroad.domain.OptimizationContext;
import com.smartroad.domain.UserProfile;
import com.smartroad.dto.OptimizationRequest;
import com.smartroad.dto.OptimizationResponse;
import com.smartroad.dto.OptimizationResult;
import com.smartroad.service.RouteOptimizationService;
import com.smartroad.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/optimize-route")
@Tag(name = "Route Optimization", description = "Compute the best route based on user preferences and candidate paths.")
public class RouteOptimizationController {

    private final RouteOptimizationService optimizationService;
    private final UserProfileService userProfileService;

    public RouteOptimizationController(RouteOptimizationService optimizationService,
                                       UserProfileService userProfileService) {
        this.optimizationService = optimizationService;
        this.userProfileService = userProfileService;
    }

    @PostMapping
    @Operation(
            summary = "Optimize a route",
            description = "Scores candidate routes, updates the user profile, and returns the best route along with scoring details."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Optimization completed",
                    content = @Content(schema = @Schema(implementation = OptimizationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<OptimizationResponse> optimizeRoute(@Valid @RequestBody OptimizationRequest request) {
        UserProfile profile = userProfileService.mergeProfile(request.getUserId(), request.getUserProfile());
        OptimizationContext context = new OptimizationContext(request.getBaseHighwayRoute(), request.getCandidateRoutes(),
                profile, request.getUserId());
        OptimizationResult result = optimizationService.optimize(context);
        return ResponseEntity.ok(new OptimizationResponse(request.getUserId(), result));
    }
}

package com.smartroad.controller;

import com.smartroad.domain.OptimizationContext;
import com.smartroad.domain.UserProfile;
import com.smartroad.dto.OptimizationRequest;
import com.smartroad.dto.OptimizationResponse;
import com.smartroad.dto.OptimizationResult;
import com.smartroad.service.RouteOptimizationService;
import com.smartroad.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/optimize-route")
public class RouteOptimizationController {

    private final RouteOptimizationService optimizationService;
    private final UserProfileService userProfileService;

    public RouteOptimizationController(RouteOptimizationService optimizationService,
                                       UserProfileService userProfileService) {
        this.optimizationService = optimizationService;
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<OptimizationResponse> optimizeRoute(@Valid @RequestBody OptimizationRequest request) {
        UserProfile profile = userProfileService.mergeProfile(request.getUserId(), request.getUserProfile());
        OptimizationContext context = new OptimizationContext(request.getBaseHighwayRoute(), request.getCandidateRoutes(),
                profile, request.getUserId());
        OptimizationResult result = optimizationService.optimize(context);
        return ResponseEntity.ok(new OptimizationResponse(request.getUserId(), result));
    }
}

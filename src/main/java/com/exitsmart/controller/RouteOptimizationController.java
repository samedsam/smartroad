package com.exitsmart.controller;

import com.exitsmart.domain.OptimizationContext;
import com.exitsmart.domain.UserProfile;
import com.exitsmart.dto.OptimizationRequest;
import com.exitsmart.dto.OptimizationResponse;
import com.exitsmart.dto.OptimizationResult;
import com.exitsmart.service.RouteOptimizationService;
import com.exitsmart.service.UserProfileService;
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

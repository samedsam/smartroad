package com.exitsmart.service;

import com.exitsmart.domain.OptimizationContext;
import com.exitsmart.domain.Route;
import com.exitsmart.dto.OptimizationResult;
import com.exitsmart.dto.ScoredRoute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RouteOptimizationService {

    private final RouteScoringService scoringService;
    private final LearningService learningService;
    private final UserProfileService userProfileService;

    public RouteOptimizationService(RouteScoringService scoringService, LearningService learningService,
                                    UserProfileService userProfileService) {
        this.scoringService = scoringService;
        this.learningService = learningService;
        this.userProfileService = userProfileService;
    }

    public OptimizationResult optimize(OptimizationContext context) {
        Route baseRoute = context.getBaseHighwayRoute();
        List<ScoredRoute> scoredRoutes = new ArrayList<>();

        for (Route route : buildRouteList(context)) {
            double score = scoringService.scoreRoute(route, baseRoute, context.getUserProfile());
            double tollSavings = baseRoute.getTotalTollCost() - route.getTotalTollCost();
            double extraMinutes = route.getTotalMinutes() - baseRoute.getTotalMinutes();
            double detourKm = route.getTotalDistanceKm() - baseRoute.getTotalDistanceKm();
            scoredRoutes.add(new ScoredRoute(route, score, tollSavings, extraMinutes, detourKm));
        }

        scoredRoutes.sort(Comparator.comparingDouble(ScoredRoute::getScore));
        ScoredRoute bestRoute = scoredRoutes.getFirst();

        learningService.learnFromChosenRoute(context.getUserProfile(), baseRoute, bestRoute.getRoute());
        if (context.getUserId() != null) {
            userProfileService.saveProfile(context.getUserId(), context.getUserProfile());
        }

        return new OptimizationResult(bestRoute, scoredRoutes, context.getUserProfile());
    }

    private List<Route> buildRouteList(OptimizationContext context) {
        List<Route> routes = new ArrayList<>();
        routes.add(context.getBaseHighwayRoute());
        routes.addAll(context.getCandidateRoutes());
        return routes;
    }
}

package com.smartroad.service;

import com.smartroad.domain.DealType;
import com.smartroad.domain.OptimizationContext;
import com.smartroad.domain.Route;
import com.smartroad.dto.OptimizationResult;
import com.smartroad.dto.ScoreBreakdown;
import com.smartroad.dto.ScoredRoute;
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
            ScoreBreakdown breakdown = scoringService.scoreRoute(route, baseRoute, context.getUserProfile());
            DealType dealType = classifyDeal(breakdown);
            String explanation = buildExplanation(breakdown, dealType);
            scoredRoutes.add(new ScoredRoute(route, breakdown.getRawScore(), breakdown, dealType, explanation));
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

    private DealType classifyDeal(ScoreBreakdown breakdown) {
        if (breakdown.getDeltaCost() > 5 && breakdown.getDeltaMinutes() <= 5 && breakdown.getComplexityPenalty() <= 2) {
            return DealType.STRONGLY_RECOMMENDED;
        }
        if (breakdown.getDeltaCost() >= 1 && breakdown.getDeltaMinutes() <= 10) {
            return DealType.RECOMMENDED;
        }
        if (breakdown.getDeltaMinutes() >= 20 || breakdown.getDeltaCost() < 0) {
            return DealType.NOT_WORTH_IT;
        }
        return DealType.NEUTRAL;
    }

    private String buildExplanation(ScoreBreakdown breakdown, DealType dealType) {
        String savingsPart = String.format("€%.2f savings vs highway", breakdown.getDeltaCost());
        String timePart = String.format("%s%.1f min", breakdown.getDeltaMinutes() >= 0 ? "+" : "", breakdown.getDeltaMinutes());
        String detourPart = String.format("%s%.1f km", breakdown.getDetourKm() >= 0 ? "+" : "", breakdown.getDetourKm());
        String dealPart;
        switch (dealType) {
            case STRONGLY_RECOMMENDED:
                dealPart = "Great tradeoff: strong savings for minor delays and low complexity.";
                break;
            case RECOMMENDED:
                dealPart = "Good balance of savings and travel time.";
                break;
            case NOT_WORTH_IT:
                dealPart = "Not attractive due to extra time or higher cost.";
                break;
            default:
                dealPart = "Marginal improvement; consider driver preference.";
                break;
        }
        return String.format("%s, time impact %s, detour %s. %s", savingsPart, timePart, detourPart, dealPart);
    }
}

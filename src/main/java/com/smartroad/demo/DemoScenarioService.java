package com.smartroad.demo;

import com.smartroad.domain.OptimizationContext;
import com.smartroad.domain.RoadSegment;
import com.smartroad.domain.Route;
import com.smartroad.domain.UserProfile;
import com.smartroad.dto.OptimizationResponse;
import com.smartroad.dto.OptimizationResult;
import com.smartroad.service.RouteOptimizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class DemoScenarioService {

    public static final String SCENARIO_1 = "SCENARIO_1";
    public static final String SCENARIO_2 = "SCENARIO_2";

    private static final Logger log = LoggerFactory.getLogger(DemoScenarioService.class);

    private final DemoRoadNetworkConfig networkConfig;
    private final RouteOptimizationService optimizationService;

    public DemoScenarioService(DemoRoadNetworkConfig networkConfig, RouteOptimizationService optimizationService) {
        this.networkConfig = networkConfig;
        this.optimizationService = optimizationService;
    }

    public Route buildBaseHighwayRoute(String scenarioId) {
        validateScenario(scenarioId);
        return new Route(new ArrayList<>(networkConfig.getHighwayBackbone()));
    }

    public List<Route> buildCandidateRoutes(String scenarioId) {
        String normalized = normalize(scenarioId);
        return switch (normalized) {
            case SCENARIO_1 -> List.of(buildCostSaverRoute());
            case SCENARIO_2 -> List.of(buildCostSaverRoute(), buildScenicSaverRoute());
            default -> throw new DemoScenarioNotFoundException(scenarioId);
        };
    }

    public UserProfile defaultDemoProfile() {
        return new UserProfile(10.0, 30, 50.0, 1.0, true, 1.0);
    }

    public OptimizationResponse optimizeScenario(String scenarioId) {
        OptimizationResult result = optimizeScenarioResult(scenarioId, defaultDemoProfile());
        return new OptimizationResponse("demo-user", result);
    }

    public OptimizationResult optimizeScenarioResult(String scenarioId, UserProfile profile) {
        Route baseRoute = buildBaseHighwayRoute(scenarioId);
        List<Route> candidates = buildCandidateRoutes(scenarioId);
        UserProfile effectiveProfile = profile != null ? profile : defaultDemoProfile();

        log.info("Running demo optimization for scenario {} with {} candidates", scenarioId, candidates.size());
        OptimizationContext context = new OptimizationContext(baseRoute, candidates, effectiveProfile, null);
        return optimizationService.optimize(context);
    }

    private Route buildCostSaverRoute() {
        List<RoadSegment> highway = networkConfig.getHighwayBackbone();
        List<RoadSegment> detour = networkConfig.getCostSaverDetour();

        List<RoadSegment> segments = new ArrayList<>();
        segments.add(highway.get(0));
        segments.add(highway.get(1));
        segments.addAll(detour);
        segments.add(highway.get(4));
        return new Route(segments);
    }

    private Route buildScenicSaverRoute() {
        List<RoadSegment> highway = networkConfig.getHighwayBackbone();
        List<RoadSegment> scenic = networkConfig.getExtendedScenicLoop();

        List<RoadSegment> segments = new ArrayList<>();
        segments.add(highway.get(0));
        segments.add(highway.get(1));
        segments.addAll(scenic);
        segments.add(highway.get(4));
        return new Route(segments);
    }

    private void validateScenario(String scenarioId) {
        normalize(scenarioId);
    }

    private String normalize(String scenarioId) {
        if (scenarioId == null) {
            throw new DemoScenarioNotFoundException("null");
        }
        String normalized = scenarioId.trim().toUpperCase(Locale.ROOT);
        if (!SCENARIO_1.equals(normalized) && !SCENARIO_2.equals(normalized)) {
            throw new DemoScenarioNotFoundException(scenarioId);
        }
        return normalized;
    }
}

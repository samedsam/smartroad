package com.exitsmart.demo;

import com.exitsmart.domain.Route;
import com.exitsmart.dto.OptimizationResponse;
import com.exitsmart.service.LearningService;
import com.exitsmart.service.RouteOptimizationService;
import com.exitsmart.service.RouteScoringService;
import com.exitsmart.service.UserProfileService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DemoScenarioServiceTest {

    private final DemoRoadNetworkConfig networkConfig = new DemoRoadNetworkConfig();
    private final RouteOptimizationService optimizationService = new RouteOptimizationService(
            new RouteScoringService(), new LearningService(), new UserProfileService());
    private final DemoScenarioService demoScenarioService = new DemoScenarioService(networkConfig, optimizationService);

    @Test
    void buildsBaseHighwayRoute() {
        Route baseRoute = demoScenarioService.buildBaseHighwayRoute(DemoScenarioService.SCENARIO_1);

        assertEquals(5, baseRoute.getSegments().size());
        assertEquals(165, baseRoute.getTotalDistanceKm(), 0.1);
        assertEquals(22, baseRoute.getTotalTollCost(), 0.1);
    }

    @Test
    void providesScenarioSpecificCandidates() {
        List<Route> scenarioOneCandidates = demoScenarioService.buildCandidateRoutes(DemoScenarioService.SCENARIO_1);
        List<Route> scenarioTwoCandidates = demoScenarioService.buildCandidateRoutes(DemoScenarioService.SCENARIO_2);

        assertEquals(1, scenarioOneCandidates.size());
        assertEquals(2, scenarioTwoCandidates.size());
        assertEquals(6, scenarioOneCandidates.getFirst().getSegments().size());
    }

    @Test
    void rejectsUnknownScenario() {
        assertThrows(DemoScenarioNotFoundException.class, () -> demoScenarioService.buildCandidateRoutes("unknown"));
    }

    @Test
    void optimizesScenarioAndReturnsResponse() {
        OptimizationResponse response = demoScenarioService.optimizeScenario(DemoScenarioService.SCENARIO_2);

        assertNotNull(response.getResult().getBestRoute());
        assertEquals(3, response.getResult().getRankedRoutes().size());
        assertEquals("demo-user", response.getUserId());
    }
}

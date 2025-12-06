package com.smartroad.service;

import com.smartroad.domain.Location;
import com.smartroad.domain.OptimizationContext;
import com.smartroad.domain.RoadSegment;
import com.smartroad.domain.RoadType;
import com.smartroad.domain.Route;
import com.smartroad.domain.UserProfile;
import com.smartroad.dto.OptimizationResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class RouteOptimizationServiceTest {

    private final RouteScoringService scoringService = new RouteScoringService();
    private final LearningService learningService = new LearningService();
    private final UserProfileService userProfileService = new UserProfileService();
    private final RouteOptimizationService optimizationService = new RouteOptimizationService(scoringService, learningService,
            userProfileService);

    @Test
    void selectsBestRouteAndPersistsProfile() {
        Route baseRoute = new Route(List.of(
                new RoadSegment("h1", new Location(0, 0, "A"), new Location(1, 1, "B"), 50, 30, 10, RoadType.HIGHWAY),
                new RoadSegment("h2", new Location(1, 1, "B"), new Location(2, 2, "C"), 50, 30, 10, RoadType.HIGHWAY)
        ));

        Route smartRoute = new Route(List.of(
                new RoadSegment("m1", new Location(0, 0, "A"), new Location(1, 1, "B"), 60, 36, 12, RoadType.HIGHWAY),
                new RoadSegment("m2", new Location(1, 1, "B"), new Location(2, 2, "C"), 40, 35, 0, RoadType.NATIONAL)
        ));

        Route fastButCostly = new Route(List.of(
                new RoadSegment("f1", new Location(0, 0, "A"), new Location(2, 2, "C"), 100, 58, 25, RoadType.HIGHWAY)
        ));

        UserProfile profile = new UserProfile(12, 20, 20, 0.5, true, 1.0);
        OptimizationContext context = new OptimizationContext(baseRoute, List.of(smartRoute, fastButCostly), profile, "device-1");

        OptimizationResult result = optimizationService.optimize(context);

        assertSame(smartRoute, result.getBestRoute().getRoute(), "Smart route should be preferred");
        assertEquals(3, result.getRankedRoutes().size());
        assertSame(result.getUpdatedProfile(), userProfileService.getOrCreateProfile("device-1"));
        assertEquals(1, smartRoute.getNumberOfHighwayExits());
    }
}

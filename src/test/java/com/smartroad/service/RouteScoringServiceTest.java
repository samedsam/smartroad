package com.smartroad.service;

import com.smartroad.domain.Location;
import com.smartroad.domain.RoadSegment;
import com.smartroad.domain.RoadType;
import com.smartroad.domain.Route;
import com.smartroad.domain.UserProfile;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RouteScoringServiceTest {

    private final RouteScoringService scoringService = new RouteScoringService();

    @Test
    void cheaperMixedRouteBeatsFullHighwayWhenConstraintsAllow() {
        Route baseRoute = new Route(List.of(
                new RoadSegment("h1", new Location(0, 0, "A"), new Location(1, 1, "B"), 50, 30, 10, RoadType.HIGHWAY),
                new RoadSegment("h2", new Location(1, 1, "B"), new Location(2, 2, "C"), 50, 30, 10, RoadType.HIGHWAY)
        ));

        Route mixedRoute = new Route(List.of(
                new RoadSegment("m1", new Location(0, 0, "A"), new Location(1, 1, "B"), 60, 36, 12, RoadType.HIGHWAY),
                new RoadSegment("m2", new Location(1, 1, "B"), new Location(2, 2, "C"), 40, 35, 0, RoadType.NATIONAL)
        ));

        UserProfile profile = new UserProfile(12, 20, 20, 0.5, true, 1.0);

        double baseScore = scoringService.scoreRoute(baseRoute, baseRoute, profile);
        double mixedScore = scoringService.scoreRoute(mixedRoute, baseRoute, profile);

        assertTrue(mixedScore < baseScore, "Mixed route should be scored better than expensive highway only route");
    }
}

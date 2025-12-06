package com.exitsmart.service;

import com.exitsmart.domain.Route;
import com.exitsmart.domain.RoadType;
import com.exitsmart.domain.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class RouteScoringService {

    public double scoreRoute(Route route, Route baseHighwayRoute, UserProfile profile) {
        double tollComponent = route.getTotalTollCost() * Math.max(profile.getTollSensitivity(), 0.1);

        double timeDeltaMinutes = Math.max(0, route.getTotalMinutes() - baseHighwayRoute.getTotalMinutes());
        double timeComponent = (timeDeltaMinutes / 60.0) * profile.getValueOfTimeEuroPerHour();

        double detourKm = Math.max(0, route.getTotalDistanceKm() - baseHighwayRoute.getTotalDistanceKm());
        double detourPenalty = detourKm <= profile.getMaxDetourKm()
                ? detourKm * 0.15
                : (profile.getMaxDetourKm() * 0.15) + (detourKm - profile.getMaxDetourKm()) * 0.4;

        double complexityComponent = route.getNumberOfHighwayExits() * profile.getComplexityFactor();

        double constraintPenalty = 0;
        if (timeDeltaMinutes > profile.getMaxExtraMinutes()) {
            constraintPenalty += (timeDeltaMinutes - profile.getMaxExtraMinutes())
                    * (profile.getValueOfTimeEuroPerHour() / 60.0) * 2;
        }
        if (detourKm > profile.getMaxDetourKm()) {
            constraintPenalty += (detourKm - profile.getMaxDetourKm()) * 0.8;
        }

        if (profile.isPreferHighways()) {
            long nonHighwaySegments = route.getSegments().stream()
                    .filter(segment -> segment.getRoadType() != RoadType.HIGHWAY)
                    .count();
            constraintPenalty += nonHighwaySegments * 0.2;
        }

        double incentiveForSavings = Math.max(0, baseHighwayRoute.getTotalTollCost() - route.getTotalTollCost()) * 0.3;

        return tollComponent + timeComponent + detourPenalty + complexityComponent + constraintPenalty - incentiveForSavings;
    }
}

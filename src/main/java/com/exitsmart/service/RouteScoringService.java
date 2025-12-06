package com.exitsmart.service;

import com.exitsmart.domain.Route;
import com.exitsmart.domain.RoadType;
import com.exitsmart.domain.UserProfile;
import com.exitsmart.dto.ScoreBreakdown;
import org.springframework.stereotype.Service;

@Service
public class RouteScoringService {

    public ScoreBreakdown scoreRoute(Route route, Route baseHighwayRoute, UserProfile profile) {
        double deltaCost = baseHighwayRoute.getTotalTollCost() - route.getTotalTollCost();
        double deltaMinutes = route.getTotalMinutes() - baseHighwayRoute.getTotalMinutes();
        double detourKm = route.getTotalDistanceKm() - baseHighwayRoute.getTotalDistanceKm();

        double tollComponent = route.getTotalTollCost() * Math.max(profile.getTollSensitivity(), 0.1);

        double timePenalty = Math.max(0, deltaMinutes) * profile.getValueOfTimeEuroPerHour() / 60.0;

        double detourPenalty = detourKm <= 0 ? 0
                : detourKm <= profile.getMaxDetourKm()
                ? detourKm * 0.15
                : (profile.getMaxDetourKm() * 0.15) + (detourKm - profile.getMaxDetourKm()) * 0.4;

        long nonHighwaySegments = route.getSegments().stream()
                .filter(segment -> segment.getRoadType() != RoadType.HIGHWAY)
                .count();

        double complexityPenalty = route.getNumberOfHighwayExits() * profile.getComplexityFactor();
        if (profile.isPreferHighways()) {
            complexityPenalty += nonHighwaySegments * 0.2;
        }

        double constraintPenalty = 0;
        if (deltaMinutes > profile.getMaxExtraMinutes()) {
            constraintPenalty += (deltaMinutes - profile.getMaxExtraMinutes())
                    * (profile.getValueOfTimeEuroPerHour() / 60.0) * 2;
        }
        if (detourKm > profile.getMaxDetourKm()) {
            constraintPenalty += (detourKm - profile.getMaxDetourKm()) * 0.8;
        }

        double incentiveForSavings = Math.max(0, deltaCost) * 0.3;

        double rawScore = tollComponent + timePenalty + detourPenalty + complexityPenalty + constraintPenalty - incentiveForSavings;

        return new ScoreBreakdown(deltaCost, deltaMinutes, detourKm, timePenalty, complexityPenalty, rawScore);
    }
}

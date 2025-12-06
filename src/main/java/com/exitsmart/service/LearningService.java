package com.exitsmart.service;

import com.exitsmart.domain.Route;
import com.exitsmart.domain.UserProfile;
import com.exitsmart.domain.UserReaction;
import com.exitsmart.dto.RouteSummaryDto;
import org.springframework.stereotype.Service;

@Service
public class LearningService {

    public void learnFromChosenRoute(UserProfile profile, Route baseRoute, Route chosenRoute) {
        double timeDelta = chosenRoute.getTotalMinutes() - baseRoute.getTotalMinutes();
        double tollDelta = chosenRoute.getTotalTollCost() - baseRoute.getTotalTollCost();

        if (tollDelta < 0 && timeDelta > 0) {
            profile.setValueOfTimeEuroPerHour(Math.max(1, profile.getValueOfTimeEuroPerHour() * 0.98));
        }

        if (timeDelta < 0) {
            profile.setValueOfTimeEuroPerHour(profile.getValueOfTimeEuroPerHour() * 1.02);
        }

        if (chosenRoute.getNumberOfHighwayExits() > baseRoute.getNumberOfHighwayExits()) {
            profile.setComplexityFactor(profile.getComplexityFactor() * 1.05);
        } else {
            profile.setComplexityFactor(Math.max(0.1, profile.getComplexityFactor() * 0.97));
        }

        if (tollDelta < 0) {
            profile.setTollSensitivity(profile.getTollSensitivity() * 1.03);
        }
    }

    public UserProfile applyFeedback(UserProfile profile, RouteSummaryDto baseRoute, RouteSummaryDto proposedRoute,
                                     UserReaction reaction) {
        UserProfile updated = copy(profile);
        double timeDelta = proposedRoute.getTotalMinutes() - baseRoute.getTotalMinutes();
        double tollDelta = proposedRoute.getTotalTollCost() - baseRoute.getTotalTollCost();
        int exitsDelta = proposedRoute.getNumberOfHighwayExits() - baseRoute.getNumberOfHighwayExits();

        switch (reaction) {
            case ACCEPTED -> handleAccepted(updated, timeDelta, tollDelta, exitsDelta);
            case IGNORED -> handleIgnored(updated);
            case ABANDONED -> handleAbandoned(updated, timeDelta, tollDelta, exitsDelta);
            default -> {
            }
        }
        return updated;
    }

    private void handleAccepted(UserProfile profile, double timeDelta, double tollDelta, int exitsDelta) {
        if (tollDelta < 0) {
            profile.setTollSensitivity(profile.getTollSensitivity() * 1.05);
        }
        if (timeDelta > 0) {
            profile.setValueOfTimeEuroPerHour(Math.max(1, profile.getValueOfTimeEuroPerHour() * 0.98));
            profile.setMaxExtraMinutes((int) Math.min(180, profile.getMaxExtraMinutes() + Math.ceil(timeDelta / 2)));
        }
        if (exitsDelta > 0) {
            profile.setComplexityFactor(Math.max(0.1, profile.getComplexityFactor() * 0.95));
        }
        profile.setPreferHighways(profile.isPreferHighways() && tollDelta > 0);
    }

    private void handleIgnored(UserProfile profile) {
        profile.setPreferHighways(true);
        profile.setValueOfTimeEuroPerHour(profile.getValueOfTimeEuroPerHour() * 1.02);
        profile.setComplexityFactor(profile.getComplexityFactor() * 1.05);
        profile.setMaxExtraMinutes(Math.max(0, profile.getMaxExtraMinutes() - 5));
    }

    private void handleAbandoned(UserProfile profile, double timeDelta, double tollDelta, int exitsDelta) {
        profile.setPreferHighways(true);
        profile.setValueOfTimeEuroPerHour(profile.getValueOfTimeEuroPerHour() * 1.05);
        profile.setComplexityFactor(profile.getComplexityFactor() * 1.1);
        profile.setMaxExtraMinutes(Math.max(0, profile.getMaxExtraMinutes() - 10));
        profile.setMaxDetourKm(Math.max(0, profile.getMaxDetourKm() - Math.max(2, timeDelta / 2)));
        if (exitsDelta > 0 || tollDelta > 0) {
            profile.setTollSensitivity(Math.max(0.1, profile.getTollSensitivity() * 0.95));
        }
    }

    private UserProfile copy(UserProfile profile) {
        if (profile == null) {
            return new UserProfile(18.0, 20, 15.0, 0.5, true, 1.0);
        }
        return new UserProfile(profile.getValueOfTimeEuroPerHour(), profile.getMaxExtraMinutes(), profile.getMaxDetourKm(),
                profile.getComplexityFactor(), profile.isPreferHighways(), profile.getTollSensitivity());
    }
}

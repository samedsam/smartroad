package com.exitsmart.service;

import com.exitsmart.domain.Route;
import com.exitsmart.domain.UserProfile;
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
}

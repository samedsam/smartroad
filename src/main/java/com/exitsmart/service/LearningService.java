package com.exitsmart.service;

import com.exitsmart.domain.Route;
import com.exitsmart.domain.UserProfile;
import com.exitsmart.domain.UserReaction;
import com.exitsmart.dto.FeedbackRequest;
import com.exitsmart.dto.FeedbackResponse;
import com.exitsmart.dto.RouteSummaryDto;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
public class LearningService {

    private static final double MIN_COMPLEXITY = 0.1;
    private static final double MIN_TOLL_SENSITIVITY = 0.1;

    public void learnFromChosenRoute(UserProfile profile, Route baseRoute, Route chosenRoute) {
        if (profile == null || baseRoute == null || chosenRoute == null) {
            return;
        }
        RouteSummaryDto baseSummary = toSummary(baseRoute);
        RouteSummaryDto proposedSummary = toSummary(chosenRoute);
        adjustProfile(profile, baseSummary, proposedSummary, UserReaction.ACCEPTED);
    }

    public FeedbackResponse applyFeedback(FeedbackRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Feedback request is required");
        }

        RouteSummaryDto baseRoute = Objects.requireNonNull(request.getBaseRoute(), "Base route summary is required");
        RouteSummaryDto proposedRoute = Objects.requireNonNull(request.getProposedRoute(), "Proposed route summary is required");
        UserProfile profile = request.getUserProfile() != null ? request.getUserProfile() : createDefaultProfile();

        UserReaction reaction = parseReaction(request.getReaction());
        String message = adjustProfile(profile, baseRoute, proposedRoute, reaction);
        return new FeedbackResponse(profile, message);
    }

    private UserReaction parseReaction(String reactionValue) {
        if (reactionValue == null) {
            throw new IllegalArgumentException("Reaction is required");
        }
        try {
            return UserReaction.valueOf(reactionValue.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unsupported reaction: " + reactionValue);
        }
    }

    private String adjustProfile(UserProfile profile, RouteSummaryDto baseRoute, RouteSummaryDto proposedRoute, UserReaction reaction) {
        double timeDelta = proposedRoute.getTotalMinutes() - baseRoute.getTotalMinutes();
        double tollDelta = proposedRoute.getTotalTollCost() - baseRoute.getTotalTollCost();
        double detourDelta = proposedRoute.getTotalDistanceKm() - baseRoute.getTotalDistanceKm();
        int exitDelta = proposedRoute.getNumberOfHighwayExits() - baseRoute.getNumberOfHighwayExits();

        switch (reaction) {
            case ACCEPTED:
                if (timeDelta > 0) {
                    profile.setMaxExtraMinutes(profile.getMaxExtraMinutes() + (int) Math.ceil(Math.min(timeDelta, 30)));
                }
                if (detourDelta > 0) {
                    profile.setMaxDetourKm(profile.getMaxDetourKm() + Math.min(detourDelta * 0.6, 10));
                }
                if (tollDelta > 0) {
                    profile.setTollSensitivity(Math.max(MIN_TOLL_SENSITIVITY, profile.getTollSensitivity() * 0.93));
                }
                if (exitDelta > 0) {
                    profile.setComplexityFactor(Math.max(MIN_COMPLEXITY, profile.getComplexityFactor() * 0.95));
                } else {
                    profile.setComplexityFactor(Math.max(MIN_COMPLEXITY, profile.getComplexityFactor() * 0.98));
                }
                if (exitDelta > 1 || detourDelta > 0) {
                    profile.setPreferHighways(false);
                }
                return "Profile adjusted based on accepted alternative.";
            case IGNORED:
                profile.setMaxExtraMinutes(Math.max(0, profile.getMaxExtraMinutes() - 2));
                profile.setMaxDetourKm(Math.max(0, profile.getMaxDetourKm() - 0.5));
                if (tollDelta > 0) {
                    profile.setTollSensitivity(profile.getTollSensitivity() * 1.03);
                }
                profile.setComplexityFactor(profile.getComplexityFactor() * (exitDelta > 0 ? 1.04 : 1.01));
                return "Profile nudged to favor simpler baseline routes.";
            case ABANDONED:
                profile.setMaxExtraMinutes(Math.max(0, profile.getMaxExtraMinutes() - (int) Math.ceil(Math.max(1, timeDelta / 2))));
                profile.setMaxDetourKm(Math.max(0, profile.getMaxDetourKm() - Math.max(1.0, detourDelta * 0.4)));
                profile.setTollSensitivity(profile.getTollSensitivity() * (tollDelta > 0 ? 1.12 : 1.06));
                profile.setComplexityFactor(profile.getComplexityFactor() * (exitDelta >= 0 ? 1.1 : 1.02));
                profile.setPreferHighways(true);
                return "Profile adjusted to penalize complex detours.";
            default:
                return "Profile unchanged.";
        }
    }

    private RouteSummaryDto toSummary(Route route) {
        return new RouteSummaryDto(route.getTotalDistanceKm(), route.getTotalMinutes(), route.getTotalTollCost(),
                route.getNumberOfHighwayExits());
    }

    private UserProfile createDefaultProfile() {
        return new UserProfile(18.0, 20, 15.0, 0.5, true, 1.0);
    }
}

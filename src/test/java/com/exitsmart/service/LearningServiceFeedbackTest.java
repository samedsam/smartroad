package com.exitsmart.service;

import com.exitsmart.domain.UserProfile;
import com.exitsmart.dto.FeedbackRequest;
import com.exitsmart.dto.FeedbackResponse;
import com.exitsmart.dto.RouteSummaryDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LearningServiceFeedbackTest {

    private final LearningService learningService = new LearningService();

    @Test
    void increasesToleranceWhenAlternativeWasAccepted() {
        UserProfile profile = new UserProfile(15.0, 20, 10.0, 0.5, true, 1.0);
        RouteSummaryDto base = new RouteSummaryDto(100.0, 60.0, 5.0, 0);
        RouteSummaryDto proposed = new RouteSummaryDto(110.0, 75.0, 8.0, 2);
        FeedbackRequest request = new FeedbackRequest(profile, base, proposed, "ACCEPTED");

        FeedbackResponse response = learningService.applyFeedback(request);

        assertEquals(35, response.getUpdatedUserProfile().getMaxExtraMinutes());
        assertEquals(16.0, response.getUpdatedUserProfile().getMaxDetourKm(), 0.01);
        assertEquals(0.93, response.getUpdatedUserProfile().getTollSensitivity(), 0.0001);
        assertEquals(0.475, response.getUpdatedUserProfile().getComplexityFactor(), 0.0001);
        assertFalse(response.getUpdatedUserProfile().isPreferHighways());
        assertEquals("Profile adjusted based on accepted alternative.", response.getMessage());
    }

    @Test
    void penalizesComplexityWhenRouteWasAbandoned() {
        UserProfile profile = new UserProfile(18.0, 25, 12.0, 0.4, false, 0.8);
        RouteSummaryDto base = new RouteSummaryDto(50.0, 40.0, 2.0, 1);
        RouteSummaryDto proposed = new RouteSummaryDto(70.0, 70.0, 5.0, 3);
        FeedbackRequest request = new FeedbackRequest(profile, base, proposed, "ABANDONED");

        FeedbackResponse response = learningService.applyFeedback(request);

        assertEquals(10, response.getUpdatedUserProfile().getMaxExtraMinutes());
        assertEquals(4.0, response.getUpdatedUserProfile().getMaxDetourKm(), 0.0001);
        assertEquals(0.896, response.getUpdatedUserProfile().getTollSensitivity(), 0.0001);
        assertEquals(0.44, response.getUpdatedUserProfile().getComplexityFactor(), 0.0001);
        assertTrue(response.getUpdatedUserProfile().isPreferHighways());
        assertEquals("Profile adjusted to penalize complex detours.", response.getMessage());
    }
}

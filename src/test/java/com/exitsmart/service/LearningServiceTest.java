package com.exitsmart.service;

import com.exitsmart.domain.UserProfile;
import com.exitsmart.domain.UserReaction;
import com.exitsmart.dto.RouteSummaryDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LearningServiceTest {

    private final LearningService learningService = new LearningService();

    @Test
    void acceptedFeedbackIncreasesTollSensitivityAndAllowsMoreTime() {
        UserProfile profile = new UserProfile(20.0, 15, 10.0, 0.5, true, 1.0);
        RouteSummaryDto base = new RouteSummaryDto(100, 60, 20, 0);
        RouteSummaryDto proposed = new RouteSummaryDto(105, 70, 10, 2);

        UserProfile updated = learningService.applyFeedback(profile, base, proposed, UserReaction.ACCEPTED);

        assertThat(updated.getTollSensitivity()).isGreaterThan(profile.getTollSensitivity());
        assertThat(updated.getMaxExtraMinutes()).isGreaterThanOrEqualTo(profile.getMaxExtraMinutes());
        assertThat(updated.getComplexityFactor()).isLessThan(profile.getComplexityFactor());
    }

    @Test
    void ignoredFeedbackPushesTowardFasterRoutes() {
        UserProfile profile = new UserProfile(18.0, 25, 12.0, 0.6, false, 1.0);
        RouteSummaryDto base = new RouteSummaryDto(80, 50, 15, 0);
        RouteSummaryDto proposed = new RouteSummaryDto(82, 52, 14, 1);

        UserProfile updated = learningService.applyFeedback(profile, base, proposed, UserReaction.IGNORED);

        assertThat(updated.isPreferHighways()).isTrue();
        assertThat(updated.getValueOfTimeEuroPerHour()).isGreaterThan(profile.getValueOfTimeEuroPerHour());
        assertThat(updated.getMaxExtraMinutes()).isLessThan(profile.getMaxExtraMinutes());
    }

    @Test
    void abandonedFeedbackPenalizesComplexityAndDetours() {
        UserProfile profile = new UserProfile(22.0, 30, 20.0, 0.4, false, 1.2);
        RouteSummaryDto base = new RouteSummaryDto(90, 55, 12, 0);
        RouteSummaryDto proposed = new RouteSummaryDto(110, 80, 15, 3);

        UserProfile updated = learningService.applyFeedback(profile, base, proposed, UserReaction.ABANDONED);

        assertThat(updated.getComplexityFactor()).isGreaterThan(profile.getComplexityFactor());
        assertThat(updated.getMaxDetourKm()).isLessThan(profile.getMaxDetourKm());
        assertThat(updated.getMaxExtraMinutes()).isLessThan(profile.getMaxExtraMinutes());
    }

    @Test
    void nullRouteSummariesReturnCopiedProfileWithoutCrashing() {
        UserProfile profile = new UserProfile(18.0, 20, 15.0, 0.5, true, 1.0);

        UserProfile updated = learningService.applyFeedback(profile, null, null, UserReaction.ACCEPTED);

        assertThat(updated).isNotSameAs(profile);
        assertThat(updated.getValueOfTimeEuroPerHour()).isEqualTo(profile.getValueOfTimeEuroPerHour());
        assertThat(updated.getMaxExtraMinutes()).isEqualTo(profile.getMaxExtraMinutes());
        assertThat(updated.getMaxDetourKm()).isEqualTo(profile.getMaxDetourKm());
        assertThat(updated.getComplexityFactor()).isEqualTo(profile.getComplexityFactor());
        assertThat(updated.isPreferHighways()).isEqualTo(profile.isPreferHighways());
        assertThat(updated.getTollSensitivity()).isEqualTo(profile.getTollSensitivity());
    }
}

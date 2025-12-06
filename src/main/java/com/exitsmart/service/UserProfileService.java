package com.exitsmart.service;

import com.exitsmart.domain.UserProfile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserProfileService {

    private final Map<String, UserProfile> profiles = new ConcurrentHashMap<>();

    public UserProfile getOrCreateProfile(String userId) {
        return profiles.computeIfAbsent(userId, id -> defaultProfile());
    }

    public UserProfile mergeProfile(String userId, UserProfile overrides) {
        UserProfile profile = getOrCreateProfile(userId);
        if (overrides != null) {
            profile.setValueOfTimeEuroPerHour(overrides.getValueOfTimeEuroPerHour());
            profile.setMaxExtraMinutes(overrides.getMaxExtraMinutes());
            profile.setMaxDetourKm(overrides.getMaxDetourKm());
            profile.setComplexityFactor(overrides.getComplexityFactor());
            profile.setPreferHighways(overrides.isPreferHighways());
            profile.setTollSensitivity(overrides.getTollSensitivity());
        }
        return profile;
    }

    public void saveProfile(String userId, UserProfile profile) {
        profiles.put(userId, profile);
    }

    private UserProfile defaultProfile() {
        return new UserProfile(18.0, 20, 15.0, 0.5, true, 1.0);
    }
}

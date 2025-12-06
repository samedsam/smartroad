package com.smartroad.domain;

import java.util.Collections;
import java.util.List;

public class Route {

    private List<RoadSegment> segments;

    public Route() {
    }

    public Route(List<RoadSegment> segments) {
        this.segments = segments;
    }

    public List<RoadSegment> getSegments() {
        return segments == null ? Collections.emptyList() : segments;
    }

    public void setSegments(List<RoadSegment> segments) {
        this.segments = segments;
    }

    public double getTotalDistanceKm() {
        return getSegments().stream().mapToDouble(RoadSegment::getDistanceKm).sum();
    }

    public double getTotalMinutes() {
        return getSegments().stream().mapToDouble(RoadSegment::getEstimatedMinutes).sum();
    }

    public double getTotalTollCost() {
        return getSegments().stream().mapToDouble(RoadSegment::getTollCost).sum();
    }

    public int getNumberOfHighwayExits() {
        RoadType previousType = null;
        int exits = 0;
        for (RoadSegment segment : getSegments()) {
            RoadType currentType = segment.getRoadType();
            if (previousType == RoadType.HIGHWAY && currentType != RoadType.HIGHWAY) {
                exits++;
            }
            previousType = currentType;
        }
        return exits;
    }
}

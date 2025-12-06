package com.exitsmart.demo;

import com.exitsmart.domain.Location;
import com.exitsmart.domain.RoadSegment;
import com.exitsmart.domain.RoadType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoRoadNetworkConfig {

    private final Location cityA = new Location(48.8566, 2.3522, "CITY_A");
    private final Location cityB = new Location(49.0097, 2.5479, "CITY_B");
    private final Location cityC = new Location(49.4944, 0.1079, "CITY_C");
    private final Location cityD = new Location(49.8941, 2.2958, "CITY_D");
    private final Location cityE = new Location(50.6292, 3.0573, "CITY_E");
    private final Location cityF = new Location(50.8503, 4.3517, "CITY_F");
    private final Location cityX = new Location(49.1829, -0.3707, "CITY_X");
    private final Location cityY = new Location(49.2583, 4.0317, "CITY_Y");
    private final Location cityM = new Location(49.6116, 6.1319, "CITY_M");
    private final Location cityN = new Location(50.1109, 8.6821, "CITY_N");

    private final RoadSegment highwayAB = new RoadSegment("H-AB", cityA, cityB, 30, 18, 4.0, RoadType.HIGHWAY);
    private final RoadSegment highwayBC = new RoadSegment("H-BC", cityB, cityC, 28, 16, 4.0, RoadType.HIGHWAY);
    private final RoadSegment highwayCD = new RoadSegment("H-CD", cityC, cityD, 35, 20, 5.0, RoadType.HIGHWAY);
    private final RoadSegment highwayDE = new RoadSegment("H-DE", cityD, cityE, 32, 19, 5.0, RoadType.HIGHWAY);
    private final RoadSegment highwayEF = new RoadSegment("H-EF", cityE, cityF, 40, 24, 4.0, RoadType.HIGHWAY);

    private final RoadSegment detourCX = new RoadSegment("N-CX", cityC, cityX, 28, 22, 0.0, RoadType.NATIONAL);
    private final RoadSegment detourXY = new RoadSegment("N-XY", cityX, cityY, 24, 18, 0.0, RoadType.NATIONAL);
    private final RoadSegment detourYE = new RoadSegment("N-YE", cityY, cityE, 30, 22, 0.0, RoadType.NATIONAL);

    private final RoadSegment scenicCM = new RoadSegment("N-CM", cityC, cityM, 36, 32, 0.0, RoadType.NATIONAL);
    private final RoadSegment scenicMN = new RoadSegment("N-MN", cityM, cityN, 34, 30, 0.0, RoadType.NATIONAL);
    private final RoadSegment scenicNE = new RoadSegment("N-NE", cityN, cityE, 38, 33, 0.0, RoadType.NATIONAL);

    public List<RoadSegment> getHighwayBackbone() {
        return List.of(highwayAB, highwayBC, highwayCD, highwayDE, highwayEF);
    }

    public List<RoadSegment> getCostSaverDetour() {
        return List.of(detourCX, detourXY, detourYE);
    }

    public List<RoadSegment> getExtendedScenicLoop() {
        return List.of(scenicCM, scenicMN, scenicNE);
    }
}

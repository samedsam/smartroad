# API Examples

This document shows request and response samples for the primary SmartRoad endpoints. Values are illustrative; adapt them to your needs.

## Optimize a route: `POST /optimize-route`
- **Description:** Scores candidate routes against a base highway route, updates the user profile, and returns the best option.
- **Sample request:**
```json
{
  "userId": "driver-42",
  "baseHighwayRoute": {
    "segments": [
      {"id": "HWY_A_B", "from": {"name": "City A"}, "to": {"name": "City B"}, "distanceKm": 80, "estimatedMinutes": 60, "tollCost": 12.5, "roadType": "HIGHWAY"},
      {"id": "HWY_B_C", "from": {"name": "City B"}, "to": {"name": "City C"}, "distanceKm": 70, "estimatedMinutes": 50, "tollCost": 10.0, "roadType": "HIGHWAY"}
    ]
  },
  "candidateRoutes": [
    {
      "segments": [
        {"id": "HWY_A_B", "from": {"name": "City A"}, "to": {"name": "City B"}, "distanceKm": 80, "estimatedMinutes": 60, "tollCost": 12.5, "roadType": "HIGHWAY"},
        {"id": "NATIONAL_B_D", "from": {"name": "City B"}, "to": {"name": "City D"}, "distanceKm": 60, "estimatedMinutes": 55, "tollCost": 0.0, "roadType": "SECONDARY"},
        {"id": "NATIONAL_D_C", "from": {"name": "City D"}, "to": {"name": "City C"}, "distanceKm": 30, "estimatedMinutes": 25, "tollCost": 0.0, "roadType": "SECONDARY"}
      ]
    }
  ],
  "userProfile": {
    "valueOfTimeEuroPerHour": 12.5,
    "maxExtraMinutes": 30,
    "maxDetourKm": 40,
    "complexityFactor": 1.0,
    "preferHighways": true,
    "tollSensitivity": 0.8
  }
}
```
- **Sample response (truncated):**
```json
{
  "userId": "driver-42",
  "bestRoute": {
    "route": {"segments": [...]},
    "score": 4.7,
    "dealType": "RECOMMENDED",
    "explanation": "€8.00 savings vs highway, time impact +18.0 min, detour +10.0 km. Good balance of savings and travel time."
  },
  "rankedRoutes": [ {"score": 4.7, "dealType": "RECOMMENDED"}, {"score": 6.2, "dealType": "NOT_WORTH_IT"} ],
  "updatedProfile": {"maxExtraMinutes": 32, "maxDetourKm": 46.0, ...}
}
```

## Send feedback: `POST /feedback`
- **Description:** Lets a driver express whether a proposed route was accepted, ignored, or abandoned; the service adapts the profile accordingly.
- **Sample request:**
```json
{
  "reaction": "ACCEPTED",
  "baseRoute": {"totalDistanceKm": 150, "totalMinutes": 110, "totalTollCost": 22.5, "numberOfHighwayExits": 1},
  "proposedRoute": {"totalDistanceKm": 165, "totalMinutes": 125, "totalTollCost": 10.0, "numberOfHighwayExits": 3},
  "userProfile": {"valueOfTimeEuroPerHour": 10.0, "maxExtraMinutes": 25, "maxDetourKm": 35.0, "complexityFactor": 1.0, "preferHighways": true, "tollSensitivity": 1.0}
}
```
- **Sample response:**
```json
{
  "updatedProfile": {
    "valueOfTimeEuroPerHour": 10.0,
    "maxExtraMinutes": 30,
    "maxDetourKm": 41.0,
    "complexityFactor": 0.98,
    "preferHighways": false,
    "tollSensitivity": 0.93
  },
  "message": "Profile adjusted based on accepted alternative."
}
```

## Run a demo scenario: `GET /demo/optimize/{scenarioId}`
- **Description:** Builds a base highway route plus predefined detours entirely in memory, then returns a full optimization response.
- **Sample usage:**
```bash
curl http://localhost:8080/demo/optimize/SCENARIO_2
```
- **Notes:** Valid scenario IDs are `SCENARIO_1` and `SCENARIO_2`. Each uses the same backbone highway with different detours.

## Simulate savings: `POST /simulate-savings`
- **Description:** Aggregates yearly savings, extra minutes, and ROI across one or more demo scenarios.
- **Sample request:**
```json
{
  "subscriptionPricePerYear": 59.99,
  "scenarios": [
    {"scenarioId": "SCENARIO_1", "tripsPerYear": 24},
    {"scenarioId": "SCENARIO_2", "tripsPerYear": 12}
  ],
  "userProfile": {"valueOfTimeEuroPerHour": 14.0, "maxExtraMinutes": 30, "maxDetourKm": 45.0, "complexityFactor": 1.0, "preferHighways": true, "tollSensitivity": 0.9}
}
```
- **Sample response (truncated):**
```json
{
  "scenarioResults": [
    {"scenarioId": "SCENARIO_1", "yearlySavings": 180.5, "yearlyExtraMinutes": 360, "dealType": "STRONGLY_RECOMMENDED", "explanation": "Good ROI"},
    {"scenarioId": "SCENARIO_2", "yearlySavings": 95.0, "yearlyExtraMinutes": 240, "dealType": "RECOMMENDED", "explanation": "Worth trying when traffic is light"}
  ],
  "totalYearlySavings": 275.5,
  "totalYearlyExtraMinutes": 600,
  "subscriptionPricePerYear": 59.99,
  "netGainAfterSubscription": 215.51,
  "roiPercentage": 359.3,
  "roiCategory": "HIGH",
  "roiExplanation": "High ROI: savings substantially exceed the subscription price."
}
```

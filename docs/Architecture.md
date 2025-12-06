# Architecture

SmartRoad is organized around a clear domain model, a service layer that performs optimization and learning, and REST controllers that expose the functionality over HTTP.

## Domain layer
- **Route**: A list of `RoadSegment` objects; exposes helpers for distance, duration, toll cost, and highway exit counting.
- **RoadSegment**: Describes a stretch between two `Location` points with distance, travel time, toll cost, and a `RoadType` (highway vs. non-highway).
- **UserProfile**: Encapsulates driver preferences such as value of time, maximum extra minutes, maximum detour kilometers, complexity factor, highway preference, and toll sensitivity.
- **ScoreBreakdown**: Holds calculated deltas (cost, minutes, detour) and penalty components used to select the best route.
- **DealType**: Classifies a proposal (e.g., strongly recommended, recommended, neutral, not worth it) to make explanations clearer.

## Optimization pipeline
1. The client posts an `OptimizationRequest` containing the base highway route, candidate routes, user ID, and optional `UserProfile` overrides.
2. `RouteOptimizationController` builds an `OptimizationContext` and delegates to `RouteOptimizationService`.
3. `RouteScoringService` calculates a `ScoreBreakdown` for each candidate compared to the base highway route (toll delta, extra minutes, detour, complexity, constraints).
4. `RouteOptimizationService` classifies the deal (via `DealType` heuristics), builds an explanation, and returns an `OptimizationResult` with the best `ScoredRoute` and ranking.
5. The chosen route is fed to `LearningService.learnFromChosenRoute`, which adapts the in-memory `UserProfile`. When a user ID is present, the merged profile is saved by `UserProfileService`.
6. The `OptimizationResponse` returned to the client includes the updated profile, best route, scoring details, and deal classification.

### Optimization sequence
```
Client -> RouteOptimizationController : POST /optimize-route
RouteOptimizationController -> UserProfileService : mergeProfile(userId, overrides)
RouteOptimizationController -> RouteOptimizationService : optimize(context)
RouteOptimizationService -> RouteScoringService : scoreRoute(route)
RouteOptimizationService -> LearningService : learnFromChosenRoute(profile, base, best)
RouteOptimizationService -> UserProfileService : saveProfile(userId, profile)
RouteOptimizationService --> RouteOptimizationController : OptimizationResult
RouteOptimizationController --> Client : OptimizationResponse
```

## Learning pipeline
- The `/feedback` endpoint accepts a `FeedbackRequest` describing the baseline vs. proposed route summary and the user reaction (`ACCEPTED`, `IGNORED`, or `ABANDONED`).
- `LearningService.updateProfile` (via `applyFeedback`) adjusts tolerance for time, detour, complexity, and toll sensitivity based on the reaction and stores the updated `UserProfile` in the response.

### Feedback sequence
```
Client -> FeedbackController : POST /feedback
FeedbackController -> LearningService : applyFeedback(request)
LearningService -> LearningService : adjust profile based on reaction
LearningService --> FeedbackController : FeedbackResponse (updated profile + message)
FeedbackController --> Client : FeedbackResponse
```

## Demo scenarios and savings simulation
- `DemoScenarioService` builds an in-memory road network with predefined detours and exposes scenario IDs (`SCENARIO_1`, `SCENARIO_2`).
- `/demo/optimize/{id}` uses `DemoScenarioService` to assemble the base highway route plus candidate detours, then reuses the standard optimization pipeline.
- `/simulate-savings` uses `SavingsSimulationService` to iterate over demo scenarios, optimize each one (`DemoScenarioService.optimizeScenarioResult`), and aggregate yearly savings, extra minutes, and ROI. `RoiAnalysisService` provides the ROI explanation and categorization.

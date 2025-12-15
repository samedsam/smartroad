# SmartRoad

SmartRoad is a Spring Boot route optimization engine that blends highways with smart detours to reduce toll costs, control extra travel time, and learn from driver feedback. It ships with demo scenarios, a savings simulator, and OpenAPI documentation for quick exploration.

## Key features
- Optimize highway routes to trim tolls while keeping extra time and complexity under control.
- Learn from user feedback to adapt proposals to personal preferences over time.
- Provide demo scenarios through an in-memory road network for easy demos and tests.
- Simulate yearly savings and subscription ROI using predefined scenarios.
- Expose a documented REST API with Swagger UI and OpenAPI descriptions.

## Architecture overview
SmartRoad is organized into a clean domain and services stack:
- **Domain model**: Route, RoadSegment, Location, UserProfile, ScoreBreakdown, DealType, and related objects that describe trips and user preferences.
- **Optimization & scoring**: Services that score each candidate route, classify the deal (e.g., recommended or not worth it), and select the best option.
- **Learning**: Feedback ingestion updates the in-memory UserProfile to reflect how drivers react to proposals.
- **Demo & simulation**: In-memory demo routes feed both the demo optimization endpoint and the savings simulator.
- **REST controllers**: OpenAPI-annotated controllers expose the optimization, feedback, demo, and savings APIs.

```
[Client / Mobile App]
         |
         v
[REST Controllers]  -->  [/optimize-route]
         |              [/feedback]
         |              [/demo/optimize/{id}]
         |              [/simulate-savings]
         v
[Services Layer]
  - RouteOptimizationService
  - RouteScoringService
  - LearningService
  - DealClassificationService
  - SavingsSimulationService
  - DemoScenarioService
         |
         v
[Domain Model]
  - Route, RoadSegment, UserProfile, ScoreBreakdown, ...
```

## Tech stack
- Java 17
- Spring Boot
- Maven
- springdoc-openapi for Swagger UI
- JUnit for tests

## Getting started
### Prerequisites
- JDK 17+
- Maven 3+

### Clone & build
```bash
git clone <your-repo-url>.git
cd <project-folder>
mvn clean install
```

### Run locally
```bash
mvn spring-boot:run
```

If your environment blocks Maven Central (e.g., HTTP 403), dependency resolution may fail; on a standard workstation Maven will fetch dependencies normally.

### Run with Docker Compose (backend + frontend)
```bash
docker-compose up --build
```

- Backend API: http://localhost:8080
- Frontend playground: http://localhost:3000

The frontend communicates with the backend using the `VITE_API_BASE_URL` environment variable (set to `http://smartroad-backend:8080` in `docker-compose.yml`).

## API overview
| Endpoint                       | Method | Description                                   |
|--------------------------------|--------|-----------------------------------------------|
| `/optimize-route`              | POST   | Optimize a route based on user preferences.   |
| `/feedback`                    | POST   | Send feedback and update the user profile.    |
| `/demo/optimize/{scenarioId}`  | GET    | Run a predefined demo scenario.               |
| `/simulate-savings`            | POST   | Simulate yearly savings and ROI.              |
| `/swagger-ui/index.html`       | GET    | Swagger UI (interactive API docs).            |

See [docs/API-Examples.md](docs/API-Examples.md) for complete payload examples.

## Usage examples
- Optimize a custom route:
  ```bash
  curl -X POST http://localhost:8080/optimize-route \
    -H "Content-Type: application/json" \
    -d '{"userId":"demo-user","baseHighwayRoute":{"segments":[]},"candidateRoutes":[{"segments":[]}]}'
  ```
- Play a demo scenario:
  ```bash
  curl http://localhost:8080/demo/optimize/SCENARIO_1
  ```
- Run the savings simulator:
  ```bash
  curl -X POST http://localhost:8080/simulate-savings \
    -H "Content-Type: application/json" \
    -d '{"scenarios":[{"scenarioId":"SCENARIO_1","tripsPerYear":24}]}'
  ```

More complete request/response samples are available in [docs/API-Examples.md](docs/API-Examples.md).

## Savings simulator
The `/simulate-savings` endpoint estimates yearly benefits from SmartRoad-style detours. Provide one or more demo scenarios with expected trip counts, plus an optional subscription price to compute ROI. The response aggregates per-scenario savings, extra minutes spent, net gain after subscription, ROI percentage, and a human-friendly explanation so teams can evaluate whether SmartRoad-like logic pays off.

## Swagger / OpenAPI
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- JSON spec: `http://localhost:8080/v3/api-docs`
- YAML spec: `http://localhost:8080/v3/api-docs.yaml`

## Roadmap / future work
- Plug in real map data (e.g., OpenStreetMap) instead of the hard-coded demo network.
- Persist user profiles, scenarios, and analytics beyond memory.
- Add authentication / API keys for multi-tenant usage.
- Expose configuration for pricing models, regions, and thresholds.

## License
SmartRoad is released under the MIT License. See [LICENSE](LICENSE) for details.

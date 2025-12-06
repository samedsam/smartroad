# SmartRoad Playground (frontend)

Simple Vite + React UI for calling the SmartRoad backend endpoints.

## Getting started

```bash
cd smartroad-ui
npm install
npm run dev
```

Configure the backend target via `VITE_API_BASE_URL` (defaults to `http://localhost:8080`). When running with Docker Compose, the environment variable is set to `http://smartroad-backend:8080` so the UI can call the API container.

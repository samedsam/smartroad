import { useState } from 'react';
import { backendUrl } from '../config/api';

interface DemoResponse {
  baseCost?: number;
  baseMinutes?: number;
  deltaCost?: number;
  deltaMinutes?: number;
  dealType?: string;
  decisionExplanation?: string;
  [key: string]: unknown;
}

function DemoOptimizePanel() {
  const [scenarioId, setScenarioId] = useState('SCENARIO_1');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [response, setResponse] = useState<DemoResponse | null>(null);

  const runDemo = async () => {
    setLoading(true);
    setError(null);
    setResponse(null);

    try {
      const res = await fetch(`${backendUrl}/demo/optimize/${scenarioId}`);
      if (!res.ok) {
        throw new Error('Request failed');
      }
      const data: DemoResponse = await res.json();
      setResponse(data);
    } catch (err) {
      console.error(err);
      setError('Unknown scenario or backend unreachable');
    } finally {
      setLoading(false);
    }
  };

  const summaryItems = [
    { label: 'Base cost', value: response?.baseCost },
    { label: 'Base minutes', value: response?.baseMinutes },
    { label: 'Δ Cost', value: response?.deltaCost },
    { label: 'Δ Minutes', value: response?.deltaMinutes },
    { label: 'Deal type', value: response?.dealType },
    { label: 'Decision', value: response?.decisionExplanation },
  ].filter((item) => item.value !== undefined && item.value !== null && item.value !== '');

  return (
    <div className="card">
      <h2>Demo Optimize</h2>
      <p className="description">Run a built-in scenario to quickly preview the optimizer output.</p>

      <div className="row-gap">
        <label>
          Choose scenario
          <select value={scenarioId} onChange={(e) => setScenarioId(e.target.value)}>
            <option value="SCENARIO_1">SCENARIO_1</option>
            <option value="SCENARIO_2">SCENARIO_2</option>
          </select>
        </label>

        <button onClick={runDemo} disabled={loading}>
          {loading ? 'Running...' : 'Run demo optimization'}
        </button>

        {error && <div className="status error">{error}</div>}

        {response && (
          <div className="response-block">
            {summaryItems.length > 0 && (
              <div className="summary-grid">
                {summaryItems.map((item) => (
                  <div className="summary-item" key={item.label}>
                    <span>{item.label}</span>
                    <strong>{String(item.value)}</strong>
                  </div>
                ))}
              </div>
            )}
            <pre>{JSON.stringify(response, null, 2)}</pre>
          </div>
        )}
      </div>
    </div>
  );
}

export default DemoOptimizePanel;

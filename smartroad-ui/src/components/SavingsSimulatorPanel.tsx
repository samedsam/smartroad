import { FormEvent, useState } from 'react';
import { backendUrl } from '../config/api';

interface ScenarioInput {
  scenarioId: string;
  tripsPerYear: number;
}

interface SavingsResponse {
  [key: string]: unknown;
}

function SavingsSimulatorPanel() {
  const [scenarios, setScenarios] = useState<ScenarioInput[]>([
    { scenarioId: 'SCENARIO_1', tripsPerYear: 12 },
    { scenarioId: 'SCENARIO_2', tripsPerYear: 24 },
  ]);
  const [subscriptionPrice, setSubscriptionPrice] = useState(59.99);
  const [valueOfTime, setValueOfTime] = useState(10);
  const [loading, setLoading] = useState(false);
  const [response, setResponse] = useState<SavingsResponse | null>(null);
  const [error, setError] = useState<string | null>(null);

  const updateScenario = (index: number, field: keyof ScenarioInput, value: string) => {
    const next = scenarios.map((scenario, i) =>
      i === index ? { ...scenario, [field]: field === 'tripsPerYear' ? Number(value) : value } : scenario
    );
    setScenarios(next);
  };

  const addScenario = () => {
    setScenarios([...scenarios, { scenarioId: 'SCENARIO_3', tripsPerYear: 6 }]);
  };

  const removeScenario = (index: number) => {
    if (scenarios.length === 1) return;
    setScenarios(scenarios.filter((_, i) => i !== index));
  };

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    setLoading(true);
    setResponse(null);
    setError(null);

    const payload = {
      scenarios,
      userProfile: {
        valueOfTimeEuroPerHour: Number(valueOfTime),
      },
      subscriptionPricePerYear: Number(subscriptionPrice),
    };

    try {
      const res = await fetch(`${backendUrl}/simulate-savings`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      if (!res.ok) {
        throw new Error('Request failed');
      }

      const data: SavingsResponse = await res.json();
      setResponse(data);
    } catch (err) {
      console.error(err);
      setError('Simulation failed. Verify scenarios or backend availability.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <h2>Savings Simulator</h2>
      <p className="description">Estimate yearly savings across demo scenarios and compare against a subscription.</p>

      <form onSubmit={handleSubmit}>
        <div className="row-gap">
          {scenarios.map((scenario, index) => (
            <div key={index} className="flex-row" style={{ alignItems: 'flex-end' }}>
              <label style={{ flex: 1 }}>
                Scenario ID
                <input
                  type="text"
                  value={scenario.scenarioId}
                  onChange={(e) => updateScenario(index, 'scenarioId', e.target.value)}
                />
              </label>
              <label style={{ width: '140px' }}>
                Trips / year
                <input
                  type="number"
                  value={scenario.tripsPerYear}
                  onChange={(e) => updateScenario(index, 'tripsPerYear', e.target.value)}
                />
              </label>
              <button type="button" onClick={() => removeScenario(index)} disabled={scenarios.length === 1}>
                Remove
              </button>
            </div>
          ))}

          <button type="button" onClick={addScenario} style={{ width: 'fit-content' }}>
            + Add scenario
          </button>
        </div>

        <label>
          Subscription price per year (€)
          <input
            type="number"
            value={subscriptionPrice}
            step="1"
            onChange={(e) => setSubscriptionPrice(parseFloat(e.target.value))}
          />
        </label>

        <label>
          Value of time (€ / hour)
          <input
            type="number"
            value={valueOfTime}
            step="0.5"
            onChange={(e) => setValueOfTime(parseFloat(e.target.value))}
          />
        </label>

        <button type="submit" disabled={loading}>
          {loading ? 'Simulating...' : 'Simulate savings'}
        </button>

        {error && <div className="status error">{error}</div>}
        {response && (
          <div className="response-block">
            <pre>{JSON.stringify(response, null, 2)}</pre>
          </div>
        )}
      </form>
    </div>
  );
}

export default SavingsSimulatorPanel;
